package ru.scarlet.notifications.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import java.time.Instant
import java.util.*

data class Token @JsonCreator constructor(

    @JsonProperty("id")
    val id: Int? = null,

    @JsonProperty("transactionId")
    val transactionId: UUID? = UUID.randomUUID(),

    @JsonProperty("code")
    var code: String = "",

    @JsonProperty("email")
    var email: String = "",

    @JsonProperty("created")
    val created: Instant,

    @JsonProperty("verified")
    var verified: Boolean,

    @JsonProperty("verifiedAt")
    var verifiedAt: Instant?

) {
    override fun toString(): String {
        return ObjectMapper().registerModule(JavaTimeModule()).writeValueAsString(this)
    }
}
