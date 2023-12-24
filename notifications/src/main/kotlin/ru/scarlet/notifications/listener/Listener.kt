package ru.scarlet.notifications.listener

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.core.Message
import org.springframework.amqp.core.MessageListener
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Service
import ru.scarlet.notifications.Constants
import ru.scarlet.notifications.dto.ProfessorContactDetails
import ru.scarlet.notifications.service.impl.EmailSenderServiceImpl
import java.nio.charset.Charset

@Service
class Listener(
    private val emailSenderService: EmailSenderServiceImpl
) : MessageListener {


    companion object {
        val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }

    @RabbitListener(queues = [Constants.NOTIFICATION_QUEUE])
    override fun onMessage(@Payload message: Message){
        logger.info("Consuming Message - {}", message.body.toString(Charset.defaultCharset()))
        val professorContactDetails = jacksonObjectMapper().readValue(message.body, ProfessorContactDetails::class.java)
        if (professorContactDetails.enableNotifyByMail){
            emailSenderService.sendEmail(professorContactDetails.email)
        }
    }
}
