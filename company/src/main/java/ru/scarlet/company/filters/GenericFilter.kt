package ru.scarlet.company.filters

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import org.slf4j.MDC
import org.springframework.stereotype.Component
import kotlin.random.Random


const val STRING_LENGTH = 11;
private val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')


@Component
class GenericFilter : Filter {
    override fun doFilter(
        servletRequest: ServletRequest,
        servletResponse: ServletResponse,
        filterChain: FilterChain
    ) {
        val randomString = (1..STRING_LENGTH)
            .map { _ -> Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("");

        MDC.put("CorrId", randomString)

        filterChain.doFilter(servletRequest, servletResponse)
    }
}