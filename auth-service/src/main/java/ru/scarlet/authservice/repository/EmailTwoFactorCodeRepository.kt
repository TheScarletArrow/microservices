package ru.scarlet.authservice.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.scarlet.authservice.entity.EmailTwoFactorCode
import java.util.*

interface EmailTwoFactorCodeRepository : JpaRepository<EmailTwoFactorCode, Int> {


    fun findByTransactionId(transactionId: UUID): EmailTwoFactorCode
}