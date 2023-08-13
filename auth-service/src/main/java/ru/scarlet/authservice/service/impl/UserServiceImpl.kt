package ru.scarlet.authservice.service.impl

import org.springframework.security.crypto.password.DelegatingPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import ru.scarlet.authservice.dto.SignUpRequest
import ru.scarlet.authservice.entity.User
import ru.scarlet.authservice.exceptions.UserNotFoundException
import ru.scarlet.authservice.repository.UserRepository
import ru.scarlet.authservice.service.UserService

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
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
}