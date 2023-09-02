package ru.scarlet.company.dtos

data class FacultyRequest(
    val name: String,
    val shortName: String,
    val deanId: Int

)
data class FacultyResponse(
    val name: String,
    val shortName: String,
    val dean: DeanResponse

)