package ru.scarlet.notifications.dto

data class ProfessorContactDetails(
    val phone: String,

    val enableNotifyByPhone: Boolean,

    val enableNotifyByMail: Boolean,

    var email: String,

    var course: CourseShort,

    var topic: MailTopic
)

enum class MailTopic{
    ADDED,REMOVED
}