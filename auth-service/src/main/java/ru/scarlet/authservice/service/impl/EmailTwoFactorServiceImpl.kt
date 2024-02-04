package ru.scarlet.authservice.service.impl

import jakarta.transaction.Transactional
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service
import ru.scarlet.authservice.config.NEW_TOKEN_ROUTING_KEY
import ru.scarlet.authservice.dto.ResponseSuccess
import ru.scarlet.authservice.dto.TwoFactorVerify
import ru.scarlet.authservice.entity.EmailTwoFactorCode
import ru.scarlet.authservice.repository.EmailTwoFactorCodeRepository
import ru.scarlet.authservice.service.EmailTwoFactorCodeService
import java.time.Clock
import java.time.Instant

@Service
open class EmailTwoFactorCodeServiceImpl(
    private val emailTwoFactorCodeRepository: EmailTwoFactorCodeRepository,
    private val rabbitTemplate: RabbitTemplate) : EmailTwoFactorCodeService {
    override fun generateCode(email: String): EmailTwoFactorCode {
        val code1 = "${(0..9).random()}${(0..9).random()}${(0..9).random()}${(0..9).random()}"

        val emailTwoFactorCode = EmailTwoFactorCode().apply {
            this.code = code1
            this.email = email

        }
        val saved = emailTwoFactorCodeRepository.save(emailTwoFactorCode)
        rabbitTemplate.convertAndSend(NEW_TOKEN_ROUTING_KEY, saved)
        return saved

    }

    @Transactional
    override fun verifyCode(email: String, verification: TwoFactorVerify) : ResponseSuccess {
        val twoFA = emailTwoFactorCodeRepository.findByTransactionId(verification.verificationId)
        if (twoFA.email == email) {
            twoFA.verified = true
            twoFA.verifiedAt = Instant.now(Clock.systemUTC())
        }
        return ResponseSuccess(if (twoFA.email==email) "SUCCESS" else "FAILED")

    }
}