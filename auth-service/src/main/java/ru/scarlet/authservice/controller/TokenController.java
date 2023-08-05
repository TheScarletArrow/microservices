package ru.scarlet.authservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.scarlet.authservice.dto.SignInRequest;
import ru.scarlet.authservice.dto.Tokens;
import ru.scarlet.authservice.dto.UsernameFromToken;
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
    @GetMapping
    public ResponseEntity<UsernameFromToken> getUsernameFromToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        log.info(token);
        String usernameFromToken = tokenService.getUsernameFromToken(token);
        return ResponseEntity.ok(new UsernameFromToken(usernameFromToken));
    }

    @PostMapping("/generate")
    public ResponseEntity<Tokens> generateTokens(@RequestBody SignInRequest request, HttpServletRequest httpServletRequest) {
        boolean userExists = userService.userExists(request.getLogin());
        if (userExists) {
            return ResponseEntity.ok(tokenService.generateTokens(request, httpServletRequest));
        } else return ResponseEntity.badRequest().body(null);
    }
}
