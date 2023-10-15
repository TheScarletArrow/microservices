package ru.scarlet.company.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ru.scarlet.company.entities.Professor

@Repository
interface ProfessorRepository : JpaRepository<Professor?, Int?> {
    @Query("select p from Professor p where lower(p.department.shortName)=:shortName")
    fun findByDepartmentShortName(shortName: String?): List<Professor?>
}