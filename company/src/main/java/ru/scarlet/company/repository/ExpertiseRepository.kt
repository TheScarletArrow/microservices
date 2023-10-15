package ru.scarlet.company.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.scarlet.company.entities.Expertise

@Repository
interface ExpertiseRepository : JpaRepository<Expertise?, Long?> {
    fun findByName(name: String?): Expertise?
}