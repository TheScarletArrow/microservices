package ru.scarlet.company.dtos

data class DepartmentRequest(
    val shortName: String,
    val name: String,
    val facultyId: Int
)
data class DepartmentResponse(
    val shortName: String,
    val name: String,
    val faculty: FacultyResponse
)