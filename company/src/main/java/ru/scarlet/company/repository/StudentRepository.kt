package ru.scarlet.company.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.scarlet.company.entities.Student
import java.util.*

interface StudentRepository : JpaRepository<Student, UUID> {


    fun findByUsername(username: String): Student
}