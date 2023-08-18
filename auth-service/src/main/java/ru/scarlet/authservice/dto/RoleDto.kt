package ru.scarlet.authservice.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import ru.scarlet.authservice.entity.Role
import java.time.LocalDateTime

data class RoleResponse(
    val name: String,
    val isActive: Boolean,
    val created: LocalDateTime
)

data class RoleRequest @JsonCreator constructor(
    @JsonProperty("name")
    val name: String
) {
    fun toRole(): Role {
        val role = Role()
        role.roleName = this.name
        return role
    }
}