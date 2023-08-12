package ru.scarlet.authservice.dto

import ru.scarlet.authservice.entity.User
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
        val user: User = User(UUID.randomUUID(),this.username, this.password)
        return user
    }
}