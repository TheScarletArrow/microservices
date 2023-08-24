package ru.scarlet.authservice.service.impl

import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.stereotype.Service
import ru.scarlet.authservice.dto.RoleRequest
import ru.scarlet.authservice.dto.RoleResponse
import ru.scarlet.authservice.entity.Role
import ru.scarlet.authservice.exceptions.RoleAlreadyExistsException
import ru.scarlet.authservice.repository.RoleRepository
import ru.scarlet.authservice.service.RoleService

@Service
@Slf4j
open class RoleServiceImpl(private val roleRepository: RoleRepository) : RoleService {

    companion object {
        @Suppress("JAVA_CLASS_ON_COMPANION")
        @JvmStatic
        private val logger = LoggerFactory.getLogger(javaClass.enclosingClass)
    }

    override fun getAll(): List<RoleResponse> {
        logger.info("${MDC.get("Corr")} getAll()")
        return roleRepository.findAll().map { it.toRoleDto() }
    }

    override fun addRole(roleRequest: RoleRequest): Role {
        logger.info("addRole($roleRequest)")
        if (!roleRepository.existsByRoleName(roleRequest.name)) {
            val role: Role = roleRequest.toRole()
            return roleRepository.save(role)
        } else throw RoleAlreadyExistsException()
    }
}