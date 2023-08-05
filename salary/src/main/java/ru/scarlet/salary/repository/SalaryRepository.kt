package ru.scarlet.salary.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.scarlet.salary.entities.Salary
import java.util.*

interface SalaryRepository : JpaRepository<Salary, UUID?> {

    fun findByUsernameOrderByDateDesc(username: String): List<Salary>
}
