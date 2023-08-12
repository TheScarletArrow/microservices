package ru.scarlet.salary.dto


import ru.scarlet.salary.entities.Salary
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*


data class SalaryIn(val date: LocalDate?, val amount: BigDecimal?, val companyName: String?) {
    fun toSalary(): Salary {
        return Salary(UUID.randomUUID(), "", date, amount, companyName)
    }

}

data class SalaryOut(val date: LocalDate?, val amount: BigDecimal?, val companyName: String?) {
    fun getSalaryOut(salaryIn: SalaryIn): SalaryOut{
        return SalaryOut(date, amount, companyName)
    }
}

