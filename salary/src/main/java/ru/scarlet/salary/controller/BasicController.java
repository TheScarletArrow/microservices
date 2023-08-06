package ru.scarlet.salary.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.scarlet.salary.dto.SignInRequest;
import ru.scarlet.salary.dto.Tokens;
import ru.scarlet.salary.services.BasicService;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/api/v1/tokens")
@Slf4j
@RequiredArgsConstructor
public class BasicController {
    private final BasicService basicService;

    @GetMapping("/")
    public ResponseEntity<Tokens> getTokens(@RequestBody SignInRequest signInRequest){
        log.info(signInRequest.toString());
       return ResponseEntity.ok(basicService.getTokens(signInRequest));
    }

    @GetMapping(value = "/user", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public ResponseEntity<String> getUsername(@RequestHeader(name = AUTHORIZATION) String auth){
        return ResponseEntity.ok(basicService.getUsernameFromToken(auth));
    }
}
