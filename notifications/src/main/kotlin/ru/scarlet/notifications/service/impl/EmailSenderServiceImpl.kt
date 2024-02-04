package ru.scarlet.notifications.service.impl

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service
import ru.scarlet.notifications.dto.CourseShort
import ru.scarlet.notifications.dto.MailTopic
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

    override fun sendEmail(destination: String, topic: MailTopic, courseShort: CourseShort) {
        logger.info("sendEmail {}", destination)

        val message = SimpleMailMessage()
        message.text = if (topic==MailTopic.ADDED)"Вы были добавлены на курс ${courseShort.courseName}!" else "Вы были исключены из курса ${courseShort.courseName}!"
        message.setTo(destination)
        message.from = from
        message.subject = if (topic==MailTopic.ADDED)"Вы были добавлены на курс ${courseShort.courseName}!" else "Вы были исключены из курса ${courseShort.courseName}!"
        javaMailSender?.send(message)
        logger.info("Sent message to {}", destination)
    }

    override fun sendEmail(destination: String, code: String) {
        logger.info("sendEmail {} {}", destination, code)

        val message = SimpleMailMessage()
        message.text = code
        message.setTo(destination)
        message.from = from
        message.subject = "OTP"
        javaMailSender?.send(message)
        logger.info("Sent message to {}", destination)
    }
}