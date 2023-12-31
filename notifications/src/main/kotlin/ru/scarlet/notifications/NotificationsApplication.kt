package ru.scarlet.notifications

import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableRabbit
class NotificationsApplication

fun main(args: Array<String>) {
    runApplication<NotificationsApplication>(*args)
}
