package ru.scarlet.authservice.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import ru.scarlet.authservice.dto.Response;

import java.time.Clock;
import java.time.LocalDateTime;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    ResponseEntity<Response> handleUserAlreadyExistsException(WebRequest request) {
        return ResponseEntity.ok(
                new Response("User already exists",
                        ((ServletWebRequest) request).getRequest().getRequestURI(),
                        400,
                        LocalDateTime.now(Clock.systemUTC())));
    }

    @ExceptionHandler(UserNotFoundException.class)
    ResponseEntity<Response> handleUserNotFoundException(WebRequest request) {
        return ResponseEntity.ok(
                new Response("User not found",
                        ((ServletWebRequest) request).getRequest().getRequestURI(),
                        404,
                        LocalDateTime.now(Clock.systemUTC())));
    }
}
