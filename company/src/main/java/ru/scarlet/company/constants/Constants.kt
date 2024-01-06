package ru.scarlet.company.constants

import kotlin.random.Random

const val STRING_LENGTH = 11
private val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

class Constants {
    companion object {
        fun randomString(): String = (1..STRING_LENGTH)
            .map { _ -> Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")
    }
}