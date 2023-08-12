package ru.scarlet.authservice.service

import ru.scarlet.authservice.dto.SignUpRequest
import ru.scarlet.authservice.entity.User

interface UserService {
    fun userExists(username: String): Boolean
    fun createUser(signUpRequest: SignUpRequest) : User
}