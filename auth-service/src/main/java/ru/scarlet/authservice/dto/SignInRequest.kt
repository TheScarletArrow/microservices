package ru.scarlet.authservice.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class SignInRequest @JsonCreator constructor(

    @JsonProperty("username")
    val username: String,

    @JsonProperty("password")
    val password: String
){}
