package ru.scarlet.notifications.configs

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import ru.scarlet.notifications.Constants
import ru.scarlet.notifications.service.EmailSenderService


@Configuration
class RabbitConfiguration(private val emailSenderService: EmailSenderService) {

    @Bean
    fun rabbitTemplate(connectionFactory: ConnectionFactory,
                       jackson2MessageConverter: Jackson2JsonMessageConverter): RabbitTemplate {
        val rabbitTemplate = RabbitTemplate(connectionFactory)
        rabbitTemplate.messageConverter = jackson2MessageConverter
        return rabbitTemplate
    }

    @Bean
    fun jackson2MessageConverter(): Jackson2JsonMessageConverter{
        return Jackson2JsonMessageConverter(jacksonObjectMapper().registerModule(JavaTimeModule()))
    }

//    @Bean
//    fun messageListenerContainer(connectionFactory: ConnectionFactory?): MessageListenerContainer? {
//        val simpleMessageListenerContainer = SimpleMessageListenerContainer()
//        simpleMessageListenerContainer.connectionFactory = connectionFactory!!
//        simpleMessageListenerContainer.setQueues(queue(), queueMail())
////        simpleMessageListenerContainer.setMessageListener(Listener(emailSenderServiceImpl))
//        return simpleMessageListenerContainer
//    }

    private fun queue(): Queue {
        return Queue(Constants.NOTIFICATION_QUEUE, false)
    }

    private fun queueMail(): Queue{
        return Queue(Constants.TOKEN_QUEUE, false)
    }

    @Bean
    fun messageConverter(): MappingJackson2HttpMessageConverter {
        return MappingJackson2HttpMessageConverter(jacksonObjectMapper().registerModule(JavaTimeModule()))
    }
}