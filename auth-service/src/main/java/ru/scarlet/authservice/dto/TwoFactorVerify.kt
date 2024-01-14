package ru.scarlet.authservice.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class TwoFactorVerify @JsonCreator constructor(
    @JsonProperty("verificationId")
    val verificationId: UUID,
    @JsonProperty("code")
    val code: String,
)
