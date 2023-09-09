package ru.scarlet.company.dtos


data class ProfessorDtoRequest(
    var name: String,


    val phone: String,


    val email: String,

    val expertise: ExpertiseDto
)

data class ProfessorContactDetails(
    val phone: String,

    val enableNotifyByPhone: Boolean,

    val enableNotifyByMail: Boolean,

    val email: String,
)

data class ProfessorDtoResponse(
    var name: String,


    val phone: String,


    val email: String,

    val expertise: ExpertiseDto
)