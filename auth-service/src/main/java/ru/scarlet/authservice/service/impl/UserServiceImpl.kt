package ru.scarlet.authservice.service.impl

import org.springframework.security.crypto.password.DelegatingPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import ru.scarlet.authservice.repository.UserRepository
import ru.scarlet.authservice.service.UserService

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {
    override fun userExists(username: String): Boolean {
        return userRepository.existsByUsername(username)
    }
}