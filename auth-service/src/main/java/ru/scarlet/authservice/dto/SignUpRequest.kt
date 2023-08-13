package ru.scarlet.authservice.dto

import ru.scarlet.authservice.entity.User
import java.time.LocalDateTime
import java.util.*

data class SignUpRequest(
    val username: String,
    val password: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val patronymic: String?
) {

    fun toUser(): User {
        val user: User = User()
        user.name = this.firstName
        user.password = this.password
        user.lastName = this.lastName
        user.patronymic = this.patronymic
        user.email = this.email
        user.username = this.username
        return user
    }
}