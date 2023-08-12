package ru.scarlet.salary.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class Tokens (
    @JsonProperty("accessToken")
    val accessToken: AccessToken,
    @JsonProperty("refreshToken")
    val refreshToken: RefreshToken
)

data class AccessToken( @JsonProperty("value")val value: String
)
data class RefreshToken(@JsonProperty("value") val value: String
)
