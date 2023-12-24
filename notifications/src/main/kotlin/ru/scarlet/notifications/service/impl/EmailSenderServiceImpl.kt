package ru.scarlet.notifications.service.impl

import org.slf4j.Logger
import org.slf4j.LoggerFactory
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

    override fun sendEmail(destination: String) {
        logger.info("sendEmail {}", destination)
    }
}