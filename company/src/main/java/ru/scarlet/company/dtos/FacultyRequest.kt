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

data class FacultyDeanGetResponse(
    val name: String,
    val shortName: String
)

data class FacultyGetAll(
    val name: String,
    val shortName: String,
    val dean: DeanResponse,
    val departments: List<DepartmentShortResponse>
)