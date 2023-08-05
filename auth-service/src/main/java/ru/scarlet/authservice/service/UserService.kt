package ru.scarlet.authservice.service

interface UserService {
    fun userExists(username: String): Boolean
}