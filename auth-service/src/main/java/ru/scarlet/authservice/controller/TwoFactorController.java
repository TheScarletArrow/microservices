package ru.scarlet.authservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.scarlet.authservice.dto.ResponseSuccess;
import ru.scarlet.authservice.dto.TwoFactorVerify;
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

    @PostMapping("/user/{userOguid}/code")
    public ResponseEntity<EmailTwoFactorCode> generate(@PathVariable UUID userOguid){
       User user =  userService.findByOguid(userOguid);
       EmailTwoFactorCode code = twoFactorService.generateCode(user.getEmail());
       return ResponseEntity.ok(code);
    }

    @PostMapping("/user/{userOguid}/verify")
    public ResponseEntity<ResponseSuccess> verify(@PathVariable UUID userOguid, @RequestBody TwoFactorVerify verification){
        User user =  userService.findByOguid(userOguid);
        return ResponseEntity.ok(twoFactorService.verifyCode(user.getEmail(), verification));
    }

}
