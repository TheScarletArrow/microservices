package ru.scarlet.company.filters

data class AuditEvent(val path: String, val correlationId : String, val code: Int)
