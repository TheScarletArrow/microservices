package ru.scarlet.salary.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import lombok.AllArgsConstructor
import lombok.Data
import lombok.EqualsAndHashCode
import lombok.ToString
import ru.scarlet.salary.dto.SalaryOut
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

@Table
@Entity
@ToString
@EqualsAndHashCode
@Data
@AllArgsConstructor
data class Salary (
    @Id
    @GeneratedValue(generator = "uuid")
    var uuid: UUID? = null,
    var username: String? = null,
    var date: LocalDate? = null,
    var amount: BigDecimal? = null,
    var companyName: String? = null) {
    fun toSalaryOut(): SalaryOut {
        return SalaryOut(date = date, amount, companyName)
    }

}