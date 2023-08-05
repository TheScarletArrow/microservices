package ru.scarlet.authservice.service.impl

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import jakarta.servlet.http.HttpServletRequest
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Service
import ru.scarlet.authservice.config.JwtConfig
import ru.scarlet.authservice.dto.AccessToken
import ru.scarlet.authservice.dto.RefreshToken
import ru.scarlet.authservice.dto.SignInRequest
import ru.scarlet.authservice.dto.Tokens
import ru.scarlet.authservice.repository.UserRepository
import ru.scarlet.authservice.service.TokenService
import ru.scarlet.authservice.service.UserService
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*
import java.util.stream.*


@Service
@Slf4j
class TokenServiceImpl(private val jwtConfig: JwtConfig,
    private val userService: UserService) : TokenService {
    override fun getUsernameFromToken(token: String) : String {
        val algorithm: Algorithm = Algorithm.HMAC256(jwtConfig.secretKey.toByteArray())
        val verifier = JWT.require(algorithm).build()
        val decodedJWT = verifier.verify(token)
        val subject = decodedJWT.subject
        return subject
    }

    override fun userExists(token: String): Boolean {
        val usernameFromToken = getUsernameFromToken(token)
        return userService.userExists(usernameFromToken);
    }

    override fun generateTokens(request: SignInRequest, httpServletRequest: HttpServletRequest) : Tokens{
        println("generating tokens")
        val algorithm = Algorithm.HMAC256(jwtConfig!!.secretKey.toByteArray())
        val date = LocalDateTime.now()
        val plusDay1 = date.plusDays(1)
        val plusDays30 = date.plusDays(30)
        val accessToken = JWT.create()
            .withSubject(request.login)
            .withExpiresAt(plusDay1.toInstant(ZoneOffset.UTC))
            .withIssuer(httpServletRequest.requestURL.toString())
//            .withClaim("roles", userByUsername.authorities.stream().map { obj: GrantedAuthority -> obj.authority }
//                .collect(Collectors.toList()))
            .sign(algorithm)
        val refreshToken = JWT.create()
            .withSubject(request.login)
            .withExpiresAt(plusDays30.toInstant(ZoneOffset.UTC))
            .withIssuer(httpServletRequest.requestURL.toString())
            .sign(algorithm)
        val tokens: MutableMap<String, String> = HashMap()
        tokens["access_token"] = accessToken
        tokens["refresh_token"] = refreshToken
        return Tokens(accessToken = AccessToken(accessToken), refreshToken = RefreshToken(refreshToken) )
    }
}