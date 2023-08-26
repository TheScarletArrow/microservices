package ru.scarlet.authservice.filters

import org.slf4j.LoggerFactory
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import org.slf4j.MDC
import org.springframework.stereotype.Component
import kotlin.random.Random

const val STRING_LENGTH = 11

@Component
class AuthFilter : Filter {
    companion object {
        @Suppress("JAVA_CLASS_ON_COMPANION")
        @JvmStatic
        private val logger = LoggerFactory.getLogger(javaClass.enclosingClass)
    }

    private val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

    override fun doFilter(httpServletRequest: ServletRequest, httpServletResponse: ServletResponse, filterChain: FilterChain) {

        val randomString = (1..STRING_LENGTH)
            .map { Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")

        MDC.put("Corr", randomString)

        filterChain.doFilter(httpServletRequest, httpServletResponse)

    }
}