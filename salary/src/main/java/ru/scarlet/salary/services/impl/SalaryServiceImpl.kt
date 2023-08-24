package ru.scarlet.salary.services.impl

import jakarta.servlet.http.HttpServletRequest
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import ru.scarlet.salary.dto.MonthyAmount
import ru.scarlet.salary.dto.SalaryIn
import ru.scarlet.salary.dto.SalaryOut
import ru.scarlet.salary.entities.Salary
import ru.scarlet.salary.repository.SalaryRepository
import ru.scarlet.salary.services.BasicService
import ru.scarlet.salary.services.SalaryService
import java.time.Month

@Service
@RequiredArgsConstructor
class SalaryServiceImpl(
    private val salaryRepository: SalaryRepository,
    private val kafkaTemplate: KafkaTemplate<String, Any>
) : SalaryService, BasicService() {


    override val salary: List<Salary?>
        get() = salaryRepository.findAll()

    @Value("\${application.kafka.auth-topic}")
    private lateinit var topic: String
    override fun createSalary(salary: SalaryIn, request: HttpServletRequest): SalaryOut {
        val header: String = getUsernameFromToken(request.getHeader(AUTHORIZATION))

        val salaryEntity = salary.toSalary()
        salaryEntity.username = header
        val salarySaved: Salary = salaryRepository.save(salaryEntity)
        return salarySaved.toSalaryOut()
    }

    override fun writeToExcel(request: HttpServletRequest, fileName: String): ByteArray {
//        var token = request.getHeader("Authorization")
//        kafkaTemplate.send()
        return ByteArray(0)
    }

    override fun getAllByUsername(username: String): List<SalaryOut> {
        return salaryRepository.findByUsernameOrderByDateDesc(username).map { it.toSalaryOut() }
    }

    override fun getGrouped(username: String): Map<Int, Map<Month, List<SalaryOut>>> {
        val groupBy = getAllByUsername(username).groupBy { it.date!!.year }.mapValues { (_, y) -> y.groupBy { it.date!!.month } }


        return groupBy

    }

    override fun findSum(username: String): List<MonthyAmount> {
        return salaryRepository.findSum(username)
    }
}
