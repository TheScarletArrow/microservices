package ru.scarlet.authservice.service

import ru.scarlet.authservice.dto.RoleRequest
import ru.scarlet.authservice.dto.RoleResponse
import ru.scarlet.authservice.entity.Role

interface RoleService {
    fun getAll(): List<RoleResponse>
    fun addRole(roleRequest: RoleRequest): Role
}