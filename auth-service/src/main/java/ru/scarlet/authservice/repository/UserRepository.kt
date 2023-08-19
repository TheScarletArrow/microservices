package ru.scarlet.authservice.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.scarlet.authservice.entity.User
import java.util.*

interface UserRepository : JpaRepository<User, UUID> {


    fun existsByUsername(username: String): Boolean

    fun findByUsername(username: String): User?
}