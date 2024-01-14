package ru.scarlet.authservice.service

import ru.scarlet.authservice.entity.EmailTwoFactorCode

interface EmailTwoFactorCodeService {
    fun generateCode(email: String): EmailTwoFactorCode
}