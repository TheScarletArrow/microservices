package ru.scarlet.authservice.dto

import java.time.LocalDateTime

data class Response(
    val message: String,
    val path: String,
    val code: Int,
    val timestamp: LocalDateTime = LocalDateTime.now()
) {
}