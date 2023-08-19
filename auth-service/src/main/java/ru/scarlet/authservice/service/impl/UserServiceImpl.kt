package ru.scarlet.authservice.service.impl

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import ru.scarlet.authservice.dto.SignUpRequest
import ru.scarlet.authservice.entity.Role
import ru.scarlet.authservice.entity.User
import ru.scarlet.authservice.exceptions.UserDoesntHaveRoleException
import ru.scarlet.authservice.exceptions.UserNotFoundException
import ru.scarlet.authservice.repository.RoleRepository
import ru.scarlet.authservice.repository.UserRepository
import ru.scarlet.authservice.service.UserService
import java.util.*
import javax.management.relation.RoleNotFoundException

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val roleRepository: RoleRepository
) : UserService {
    override fun userExists(username: String): Boolean {
        return userRepository.existsByUsername(username)
    }

    override fun createUser(signUpRequest: SignUpRequest): User {

        val user = signUpRequest.toUser()
        user.password = passwordEncoder.encode(user.password)
        return userRepository.save(user)
    }

    override fun getUserByUsername(username: String): User {
        val user = userRepository.findByUsername(username)
        return user ?: throw UserNotFoundException()
    }

    override fun addRoleToUser(userId: UUID, roleId: Int): User {
        val user = userRepository.findById(userId).orElseThrow { UserNotFoundException() }
        val role = roleRepository.findById(roleId).orElseThrow { RoleNotFoundException() }

        if (user.roles!=null) {
            val roles = user.roles.add(role)
        }
        else {
            val roles = emptyList<Role>()
        }

        return userRepository.save(user)

    }

    override fun removeRoleFromUser(userId: UUID, roleId: Int): User {
        val user = userRepository.findById(userId).orElseThrow { UserNotFoundException() }
        val role = roleRepository.findById(roleId).orElseThrow { RoleNotFoundException() }

        if (user.roles!=null) {
            if (user.roles.contains(role)) {
                val roles = user.roles.remove(role)
            }
            else throw UserDoesntHaveRoleException()
        }
        else {
            val roles = emptyList<Role>()
        }

        return userRepository.save(user)
    }
}