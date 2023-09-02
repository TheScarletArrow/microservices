package ru.scarlet.company.dtos

data class CourseResponse(
    val courseCode: String,
    val courseName: String,
    val department: DepartmentDtoCourse,
    val taughtByProfessors: List<ProfessorDtoCourse>
)

data class DepartmentDtoCourse(val shortName: String, val name: String)

data class ProfessorDtoCourse(val name: String, val expertise: ExpertiseDto) {}

data class ExpertiseDto(val name: String)