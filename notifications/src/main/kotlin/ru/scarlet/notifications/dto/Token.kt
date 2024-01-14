package ru.scarlet.notifications.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import java.time.Instant

data class Token @JsonCreator constructor(

    @JsonProperty("id")
    val id: Int? = null,

    @JsonProperty("code")
    var code: String = "",

    @JsonProperty("email")
    var email: String = "",

    @JsonProperty("created")
    val created: Instant


) {
    override fun toString(): String {
        return ObjectMapper().registerModule(JavaTimeModule()).writeValueAsString(this)
    }
}
