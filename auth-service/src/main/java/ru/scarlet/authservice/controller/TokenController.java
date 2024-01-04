package ru.scarlet.authservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.scarlet.authservice.dto.SignInRequest;
import ru.scarlet.authservice.dto.Tokens;
import ru.scarlet.authservice.dto.UsernameFromToken;
import ru.scarlet.authservice.exceptions.TokenNotFoundException;
import ru.scarlet.authservice.exceptions.UserNotFoundException;
import ru.scarlet.authservice.service.TokenService;
import ru.scarlet.authservice.service.UserService;

@RestController
@RequestMapping("/api/v1/tokens")
@RequiredArgsConstructor
@Slf4j
public class TokenController {
    private final TokenService tokenService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/user")
    public ResponseEntity<UsernameFromToken> getUsernameFromToken(HttpServletRequest request) {
        log.info("getUsernameFromToken");
        String token = request.getHeader("Authorization").replace("Bearer ", "");
           log.info("token={}", token);
           String usernameFromToken = tokenService.getUsernameFromToken(token);
        if (tokenService.validateToken(usernameFromToken, token)){
            log.info("usernameFromToken={}", usernameFromToken);
           return ResponseEntity.ok(new UsernameFromToken(usernameFromToken));
       } else throw new TokenNotFoundException();
    }

    @PostMapping("/generate")
    public ResponseEntity<Tokens> generateTokens(@RequestBody SignInRequest request, HttpServletRequest httpServletRequest) {
        boolean userExists = userService.userExists(request.getUsername());
        if (userExists) {
            Tokens body = tokenService.generateTokens(request, httpServletRequest);
            log.info(body.toString());
            return ResponseEntity.ok(body);
        } else throw new UserNotFoundException();
    }


}
