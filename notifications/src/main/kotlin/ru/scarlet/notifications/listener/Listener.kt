package ru.scarlet.notifications.listener

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Service
import ru.scarlet.notifications.Constants
import ru.scarlet.notifications.dto.ProfessorContactDetails
import ru.scarlet.notifications.dto.Token
import ru.scarlet.notifications.service.impl.EmailSenderServiceImpl

@Service
class Listener(
    private val emailSenderService: EmailSenderServiceImpl
)  {


    companion object {
        val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }

    @RabbitListener(queues = [Constants.NOTIFICATION_QUEUE])
    fun onMessage1(@Payload message: ProfessorContactDetails){
//        logger.info("Consuming Message - {}", message.body.toString(Charset.defaultCharset()))
//        val professorContactDetails = jacksonObjectMapper().readValue(message.body, ProfessorContactDetails::class.java)
        if (message.enableNotifyByMail){
            emailSenderService.sendEmail(message.email)
        }
    }

    @RabbitListener(queues = [Constants.TOKEN_QUEUE])
    fun onMessage(@Payload token: Token){
        logger.info("Consuming Message - {}", token)
//        val token = ObjectMapper().registerModules(JavaTimeModule()).readValue(tokenString, Token::class.java)
        emailSenderService.sendEmail(token.email, token.code)
        logger.info("Sent message to {}", token.email)
    }
}
