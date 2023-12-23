package ru.scarlet.notifications.listener

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.core.Message
import org.springframework.amqp.core.MessageListener
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service
import ru.scarlet.notifications.Constants
import java.nio.charset.Charset

@RabbitListener(queues = [Constants.NOTIFICATION_QUEUE])
@Service
class Listener : MessageListener {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }

    override fun onMessage(message: Message){
        logger.info("Consuming Message - {}", message.body.toString(Charset.defaultCharset()))
    }
}
