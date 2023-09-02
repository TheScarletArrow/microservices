package ru.scarlet.company.dtos

data class DeanRequest(
    val firstName: String,
    val lastName: String,
    val patronymic: String?
)
data class DeanResponse(
    val firstName: String,
    val lastName: String,
    val patronymic: String?
)