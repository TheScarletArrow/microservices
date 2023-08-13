package ru.scarlet.authservice.dto

data class SignInRequest(
    val username: String,
    val password: String
){}
