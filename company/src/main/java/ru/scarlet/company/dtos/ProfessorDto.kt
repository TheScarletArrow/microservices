package ru.scarlet.company.dtos

import jakarta.validation.constraints.NotNull


data class ProfessorDtoRequest(
    @NotNull
    var name: String,

    @NotNull
    val phone: String,

    @NotNull
    val email: String,

    @NotNull
    val expertiseId: Long
)

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

data class ProfessorDtoResponse(
    var name: String,


    val phone: String,


    val email: String,

    val expertiseId: Long
)