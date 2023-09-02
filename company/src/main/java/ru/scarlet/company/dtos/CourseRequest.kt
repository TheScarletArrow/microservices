package ru.scarlet.company.dtos

data class CourseRequest(
    val courseCode: String,
    val courseName: String,
    val departmentOid: Int,
    val professors: List<Int>
)
