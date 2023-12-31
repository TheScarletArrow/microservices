package ru.scarlet.fileservice.models


data class ErrorDetails(
    val timestamp: Long,
    val path: String,
    val message: String,
    val code: Int,
    val correlationId: String?
)

