package ru.scarlet.notifications.service.impl

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service
import ru.scarlet.notifications.service.EmailSenderService

@Service
class EmailSenderServiceImpl(
    private val javaMailSender: JavaMailSender?
) : EmailSenderService {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }

    @Value("\${spring.mail.username}")
    private val from: String? = null

    override fun sendEmail(destination: String) {
        logger.info("sendEmail {}", destination)

        val message = SimpleMailMessage()
        message.text = "Приветствуем на портале!"
        message.setTo(destination)
        message.from = from
        message.subject = "Приветствуем на портале"
        javaMailSender?.send(message)
        logger.info("Sent message to {}", destination)
    }
}