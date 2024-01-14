package ru.scarlet.authservice.service

import ru.scarlet.authservice.dto.SignUpRequest
import ru.scarlet.authservice.entity.User
import java.util.*

interface UserService {
    fun userExists(username: String): Boolean
    fun createUser(signUpRequest: SignUpRequest) : User
    fun getUserByUsername(username: String): User
    fun addRoleToUser(userId: UUID, roleId: Int): User
    fun removeRoleFromUser(userId: UUID, roleId: Int): User
    fun findByOguid(userOguid: UUID): User
}