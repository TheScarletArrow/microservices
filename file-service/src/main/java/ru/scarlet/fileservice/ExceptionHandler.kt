package ru.scarlet.fileservice

import jakarta.servlet.http.HttpServletRequest
import org.slf4j.MDC
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import ru.scarlet.fileservice.models.ErrorDetails
import java.io.IOException
import java.time.Instant

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(IOException::class)
    fun handleIOException(ex: IOException, request: HttpServletRequest): ResponseEntity<ErrorDetails> {
        return ResponseEntity<ErrorDetails>(
            ErrorDetails(
                Instant.now().toEpochMilli(), request.requestURI, ex.message!!, 404, MDC.get("CorrId")?:""
            ), HttpStatus.NOT_FOUND
        )
    }
}