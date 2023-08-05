package ru.scarlet.authservice.dto

data class Tokens (
    val accessToken: AccessToken,
    val refreshToken: RefreshToken
)

data class AccessToken( val value: String
)
data class RefreshToken( val value: String
)
