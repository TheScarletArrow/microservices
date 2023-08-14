package ru.scarlet.salary.services

import jakarta.servlet.http.HttpServletRequest
import ru.scarlet.salary.dto.SalaryIn
import ru.scarlet.salary.dto.SalaryOut
import ru.scarlet.salary.entities.Salary

interface SalaryService {
    val salary: List<Salary?>?
    fun createSalary(salary: SalaryIn, request: HttpServletRequest): SalaryOut
    fun writeToExcel(request: HttpServletRequest, fileName: String): ByteArray
    fun getAllByUsername(username: String): List<SalaryOut>


}
