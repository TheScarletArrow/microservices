package ru.scarlet.company.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import ru.scarlet.company.dtos.SignInRequest;
import ru.scarlet.company.dtos.Tokens;
import ru.scarlet.company.dtos.UsernameFromToken;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@FeignClient(value = "auth-service", url = "localhost:8080/api/v1/")
public interface AuthClient {
    @PostMapping(value = "tokens/generate", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    ResponseEntity<Tokens> getTokens(@RequestBody SignInRequest signInRequest);

    @GetMapping(value = "tokens/user", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    ResponseEntity<UsernameFromToken> getUsername(@RequestHeader(name = AUTHORIZATION) String authHeader);


}
