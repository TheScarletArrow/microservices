package ru.scarlet.authservice.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.scarlet.authservice.entity.EmailTwoFactorCode

interface EmailTwoFactorCodeRepository : JpaRepository<EmailTwoFactorCode, Int> {
}