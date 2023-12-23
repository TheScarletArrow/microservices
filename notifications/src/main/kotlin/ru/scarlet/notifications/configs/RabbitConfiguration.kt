package ru.scarlet.notifications.configs

import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.rabbit.listener.MessageListenerContainer
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.scarlet.notifications.Constants
import ru.scarlet.notifications.listener.Listener


@Configuration
class RabbitConfiguration {

    @Bean
    fun rabbitTemplate(connectionFactory: ConnectionFactory,
                       jackson2MessageConverter: Jackson2JsonMessageConverter): RabbitTemplate {
        val rabbitTemplate = RabbitTemplate(connectionFactory)
        rabbitTemplate.messageConverter = jackson2MessageConverter
        return rabbitTemplate
    }

    @Bean
    fun jackson2MessageConverter(): Jackson2JsonMessageConverter{
        return Jackson2JsonMessageConverter()
    }

    @Bean
    fun messageListenerContainer(connectionFactory: ConnectionFactory?): MessageListenerContainer? {
        val simpleMessageListenerContainer = SimpleMessageListenerContainer()
        simpleMessageListenerContainer.connectionFactory = connectionFactory!!
        simpleMessageListenerContainer.setQueues(queue())
        simpleMessageListenerContainer.setMessageListener(Listener())
        return simpleMessageListenerContainer
    }

    private fun queue(): Queue {
        return Queue(Constants.NOTIFICATION_QUEUE, false)
    }
}