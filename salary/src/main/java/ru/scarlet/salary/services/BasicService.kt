package ru.scarlet.salary.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.scarlet.salary.client.AuthClient
import ru.scarlet.salary.dto.SignInRequest
import ru.scarlet.salary.dto.Tokens

@Service
open class BasicService {
    @Autowired
    private lateinit var authClient: AuthClient
    fun getTokens(signInRequest: SignInRequest) : Tokens? {
        return authClient.getTokens(signInRequest).body
    }
}