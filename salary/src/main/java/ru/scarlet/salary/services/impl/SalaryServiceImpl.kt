package ru.scarlet.salary.services.impl

import jakarta.servlet.http.HttpServletRequest
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import ru.scarlet.salary.dto.SalaryIn
import ru.scarlet.salary.dto.SalaryOut
import ru.scarlet.salary.entities.Salary
import ru.scarlet.salary.repository.SalaryRepository
import ru.scarlet.salary.services.SalaryService

@Service
@RequiredArgsConstructor
class SalaryServiceImpl(private val salaryRepository: SalaryRepository, private val kafkaTemplate: KafkaTemplate<String, Any>) : SalaryService {
    override fun getAll(httpServletRequest: HttpServletRequest): List<SalaryOut> {
        val header: String = httpServletRequest.getHeader("username")
        return salaryRepository.findByUsernameOrderByDateDesc(header).map(Salary::toSalaryOut)
    }

    override val salary: List<Salary?>
        get() = salaryRepository.findAll()

    @Value("\${application.kafka.auth-topic}")
    private lateinit var topic: String
    override fun createSalary(salary: SalaryIn, request: HttpServletRequest): SalaryOut {
        val header: String = request.getHeader("username")

        val salaryEntity = salary.toSalary()
        salaryEntity.username=header
        val salarySaved: Salary = salaryRepository.save(salaryEntity)
        return salarySaved.toSalaryOut()
    }

    override fun writeToExcel(request: HttpServletRequest, reportName: String): ByteArray {
//        var token = request.getHeader("Authorization")
//        kafkaTemplate.send()
        return ByteArray(0)
    }
}
