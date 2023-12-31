package ru.scarlet.authservice.service

import jakarta.servlet.http.HttpServletRequest
import ru.scarlet.authservice.dto.SignInRequest
import ru.scarlet.authservice.dto.Tokens

interface TokenService {
    fun getUsernameFromToken(token: String): String

    fun userExists(token: String): Boolean
    fun generateTokens(request: SignInRequest, httpServletRequest: HttpServletRequest): Tokens
    fun validateToken(username: String, token: String): Boolean?
}