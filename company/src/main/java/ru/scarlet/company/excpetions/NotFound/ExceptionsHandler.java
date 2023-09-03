package ru.scarlet.company.excpetions.NotFound;

import jakarta.servlet.http.HttpServletRequest;
import java.time.Instant;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.scarlet.company.dtos.ErrorDetails;

@RestControllerAdvice
public class ExceptionsHandler {

	@ExceptionHandler({CourseNotFoundException.class, DeanNotFoundException.class, FacultyNotFoundException.class})
	public ResponseEntity<ErrorDetails> handleCourseNotFoundException(HttpServletRequest request, CourseNotFoundException ex){
		return new ResponseEntity<>(new ErrorDetails(Instant.now().toEpochMilli(), request.getRequestURI(), ex.getMessage(), 404, MDC.get("CorrId")), HttpStatus.NOT_FOUND);
	}
}
