package ru.scarlet.company.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.scarlet.company.entities.Faculty

@Repository
interface FacultyRepository : JpaRepository<Faculty?, Int?> {
    fun findByDeanOid(oid: Int?): Faculty?

}