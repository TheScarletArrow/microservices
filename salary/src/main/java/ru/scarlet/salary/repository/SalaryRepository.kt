package ru.scarlet.salary.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import ru.scarlet.salary.dto.MonthyAmount
import ru.scarlet.salary.entities.Salary
import java.util.*

interface SalaryRepository : JpaRepository<Salary, UUID?> {

    fun findByUsernameOrderByDateDesc(username: String): List<Salary>

    @Query(value = "select NEW ru.scarlet.salary.dto.MonthyAmount(YEAR(t.date), MONTH(t.date), SUM(t.amount))  " +
            "from Salary t where t.username=:username " +
            "group by year(t.date), month(t.date) " +
            "order by year(t.date), month(t.date)")
    fun findSum(username: String) : List<MonthyAmount>
}
