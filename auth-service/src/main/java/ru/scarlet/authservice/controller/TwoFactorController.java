package ru.scarlet.authservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.scarlet.authservice.entity.EmailTwoFactorCode;
import ru.scarlet.authservice.entity.User;
import ru.scarlet.authservice.service.EmailTwoFactorCodeService;
import ru.scarlet.authservice.service.UserService;

import java.util.UUID;

@RestController
@RequestMapping("/2fa")
@RequiredArgsConstructor
@Slf4j
public class TwoFactorController {

    private final EmailTwoFactorCodeService twoFactorService;
    private final UserService userService;

    @PostMapping("{userOguid}/code")
    public ResponseEntity<EmailTwoFactorCode> generate(@PathVariable UUID userOguid){
       User user =  userService.findByOguid(userOguid);
       EmailTwoFactorCode code = twoFactorService.generateCode(user.getEmail());
       return ResponseEntity.ok(code);
    }
}
