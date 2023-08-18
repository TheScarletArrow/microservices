package ru.scarlet.authservice.service.impl

import org.springframework.stereotype.Service
import ru.scarlet.authservice.dto.RoleRequest
import ru.scarlet.authservice.dto.RoleResponse
import ru.scarlet.authservice.entity.Role
import ru.scarlet.authservice.exceptions.RoleAlreadyExistsException
import ru.scarlet.authservice.repository.RoleRepository
import ru.scarlet.authservice.service.RoleService

@Service
open class RoleServiceImpl(private val roleRepository: RoleRepository) : RoleService {
    override fun getAll(): List<RoleResponse> {
        return roleRepository.findAll().map { it.toRoleDto() }
    }

    override fun addRole(roleRequest: RoleRequest): Role {
        if (!roleRepository.existsByRoleName(roleRequest.name)) {
            val role: Role = roleRequest.toRole()
            return roleRepository.save(role)
        } else throw RoleAlreadyExistsException()
    }
}