package ru.scarlet.authservice.service.impl

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import jakarta.servlet.http.HttpServletRequest
import lombok.extern.slf4j.Slf4j
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import ru.scarlet.authservice.config.JwtConfig
import ru.scarlet.authservice.dto.AccessToken
import ru.scarlet.authservice.dto.RefreshToken
import ru.scarlet.authservice.dto.SignInRequest
import ru.scarlet.authservice.dto.Tokens
import ru.scarlet.authservice.service.TokenService
import ru.scarlet.authservice.service.UserService
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneOffset


@Service
@Slf4j
class TokenServiceImpl(private val jwtConfig: JwtConfig,
    private val userService: UserService,
    private val redisTemplate: RedisTemplate<String, String>) : TokenService {
    override fun getUsernameFromToken(token: String) : String {
        val algorithm: Algorithm = Algorithm.HMAC256(jwtConfig.secretKey.toByteArray())
        val verifier = JWT.require(algorithm).build()
        val decodedJWT = verifier.verify(token)
        val subject = decodedJWT.subject
        return subject
    }

    override fun userExists(token: String): Boolean {
        val usernameFromToken = getUsernameFromToken(token)
        return userService.userExists(usernameFromToken)
    }

    override fun generateTokens(request: SignInRequest, httpServletRequest: HttpServletRequest) : Tokens{
        println("generating tokens")
        val accessToken = if (redisTemplate.opsForValue()[request.username + "_ACCESS"] == null) {
            val algorithm = Algorithm.HMAC256(jwtConfig.secretKey.toByteArray())
            val date = LocalDateTime.now()
            val plusDay14 = date.plusDays(jwtConfig.accessTokenExpirationAfterDays ?: 14)
            val accessToken1 = JWT.create()
                .withSubject(request.username)
                .withExpiresAt(plusDay14.toInstant(ZoneOffset.UTC))
                .withIssuer(httpServletRequest.requestURL.toString())
//            .withClaim("roles", userByUsername.authorities.stream().map { obj: GrantedAuthority -> obj.authority }
//                .collect(Collectors.toList()))
                .sign(algorithm)
//            val refreshToken = JWT.create()
//                .withSubject(request.username)
//                .withExpiresAt(plusDays30.toInstant(ZoneOffset.UTC))
//                .withIssuer(httpServletRequest.requestURL.toString())
//                .sign(algorithm)

//            tokens["refresh_token"] = refreshToken
//            println("generated $accessToken and $refreshToken")
            redisTemplate.opsForValue()[request.username + "_ACCESS", accessToken1] = Duration.ofDays(jwtConfig.accessTokenExpirationAfterDays!!)
            accessToken1
        } else {
             redisTemplate.opsForValue()[request.username + "_ACCESS"]
        }
        val tokens: MutableMap<String, String> = HashMap()
        tokens["access_token"] = accessToken
        val refreshToken = if (redisTemplate.opsForValue()[request.username + "REFRESH"] == null) {
            val algorithm = Algorithm.HMAC256(jwtConfig.secretKey.toByteArray())
            val date = LocalDateTime.now()
            val plusDays30 = date.plusDays(jwtConfig.refreshTokenExpirationAfterDays ?: 30)

            val refreshToken = JWT.create()
                .withSubject(request.username)
                .withExpiresAt(plusDays30.toInstant(ZoneOffset.UTC))
                .withIssuer(httpServletRequest.requestURL.toString())
                .sign(algorithm)

            redisTemplate.opsForValue()[request.username + "_REFRESH", refreshToken] = Duration.ofDays(jwtConfig.refreshTokenExpirationAfterDays!!)
            refreshToken
        } else {
            redisTemplate.opsForValue()[request.username + "_REFRESH"]
        }
        tokens["refresh_token"] = refreshToken
        return Tokens(accessToken = AccessToken(accessToken), refreshToken = RefreshToken(refreshToken) )
    }
}