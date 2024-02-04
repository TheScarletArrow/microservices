package ru.scarlet.notifications.service

import ru.scarlet.notifications.dto.CourseShort
import ru.scarlet.notifications.dto.MailTopic

interface EmailSenderService {

    fun sendEmail(destination: String, topic: MailTopic, courseShort: CourseShort)

    fun sendEmail(destination: String, code: String)
}