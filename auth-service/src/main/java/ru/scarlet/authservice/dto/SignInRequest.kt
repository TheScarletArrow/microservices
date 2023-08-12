package ru.scarlet.authservice.dto

data class SignInRequest(
    val login: String,
    val password: String
){}
