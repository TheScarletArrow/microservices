package ru.scarlet.company.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.scarlet.company.entities.Department

@Repository
interface DepartmentRepository : JpaRepository<Department?, Int?> {
    fun findByShortName(shortName: String?): Department?
}