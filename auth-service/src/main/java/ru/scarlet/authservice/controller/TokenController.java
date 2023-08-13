package ru.scarlet.authservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.scarlet.authservice.dto.SignInRequest;
import ru.scarlet.authservice.dto.SignUpRequest;
import ru.scarlet.authservice.dto.Tokens;
import ru.scarlet.authservice.dto.UsernameFromToken;
import ru.scarlet.authservice.entity.User;
import ru.scarlet.authservice.exceptions.UserAlreadyExistsException;
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
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        log.info(token);
        String usernameFromToken = tokenService.getUsernameFromToken(token);
        return ResponseEntity.ok(new UsernameFromToken(usernameFromToken));
    }

    @PostMapping("/generate")
    public ResponseEntity<Tokens> generateTokens(@RequestBody SignInRequest request, HttpServletRequest httpServletRequest) {
        boolean userExists = userService.userExists(request.getLogin());
        if (userExists) {
            Tokens body = tokenService.generateTokens(request, httpServletRequest);
            log.info(body.toString());
            return ResponseEntity.ok(body);
        } else return ResponseEntity.badRequest().body(null);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest signUpRequest, HttpServletRequest httpServletRequest){
        if (!userService.userExists(signUpRequest.getUsername())) {
            User user = userService.createUser(signUpRequest);
            return new ResponseEntity<>(user, HttpStatusCode.valueOf(201));
        } else throw new UserAlreadyExistsException();
    }
}
