package ru.scarlet.company.entities

import java.time.Instant

data class AuditEntity(val traceId: String, val spanId: String, val dateTime: Instant,)
