package ru.scarlet.company.dtos


data class ProfessorDtoRequest(
    var name: String,


    val phone: String,


    val email: String,

    val expertise: ExpertiseDto
)