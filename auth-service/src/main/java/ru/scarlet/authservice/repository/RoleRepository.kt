package ru.scarlet.authservice.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.scarlet.authservice.entity.Role

interface RoleRepository : JpaRepository<Role, Int> {
    fun existsByRoleName(name: String): Boolean
}