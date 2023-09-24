package ru.scarlet.company.filters

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.MDC
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.scarlet.company.configs.NEW_AUDIT_ROUTING_KEY
import kotlin.random.Random


const val STRING_LENGTH = 11
private val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')


@Component
class GenericFilter : Filter {

    @Autowired
    private lateinit var rabbitTemplate: RabbitTemplate
    override fun doFilter(
        servletRequest: ServletRequest,
        servletResponse: ServletResponse,
        filterChain: FilterChain
    ) {
        val httpServletRequest = servletRequest as HttpServletRequest
        if (!httpServletRequest.servletPath.contains("prometheus")) {

        val randomString = (1..STRING_LENGTH)
            .map { _ -> Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")

        MDC.put("CorrId", randomString)
        val event = AuditEvent( httpServletRequest.servletPath, randomString, (servletResponse as HttpServletResponse).status )
        filterChain.doFilter(servletRequest, servletResponse)
        rabbitTemplate.convertAndSend(NEW_AUDIT_ROUTING_KEY, event)}
        else {
            filterChain.doFilter(servletRequest, servletResponse)

        }
    }
}