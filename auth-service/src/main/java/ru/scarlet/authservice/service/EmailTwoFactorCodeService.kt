package ru.scarlet.authservice.service

import ru.scarlet.authservice.dto.ResponseSuccess
import ru.scarlet.authservice.dto.TwoFactorVerify
import ru.scarlet.authservice.entity.EmailTwoFactorCode

interface EmailTwoFactorCodeService {
    fun generateCode(email: String): EmailTwoFactorCode
    fun verifyCode(email: String, verification: TwoFactorVerify) : ResponseSuccess
}