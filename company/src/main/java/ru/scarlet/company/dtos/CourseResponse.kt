package ru.scarlet.company.dtos

import com.fasterxml.jackson.annotation.JsonCreator

data class CourseResponse(
    val oid: Int,
    val courseCode: String,
    val courseName: String,
    val department: DepartmentDtoCourse,
    val taughtByProfessors: List<ProfessorDtoCourse>
)

data class DepartmentDtoCourse(val shortName: String, val name: String)

data class ProfessorDtoCourse @JsonCreator constructor(val name: String, val expertise: ExpertiseDto) {}

data class ExpertiseDto @JsonCreator constructor(val name: String, val shortName: String)