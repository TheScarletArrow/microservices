package ru.scarlet.company.dtos


data class ProfessorDtoRequest(
    var name: String,


    val phone: String,


    val email: String,

    val expertiseId: Long
)

data class ProfessorContactDetails(
    val phone: String,

    val enableNotifyByPhone: Boolean,

    val enableNotifyByMail: Boolean,

    var email: String,

    var course: CourseShort
)

data class ProfessorDtoResponse(
    var name: String,


    val phone: String,


    val email: String,

    val expertise: ExpertiseDto
)