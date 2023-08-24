package ru.scarlet.salary.dto

import java.math.BigDecimal

data class MonthyAmount(val year: Int, val month: Int, val amount: BigDecimal)
