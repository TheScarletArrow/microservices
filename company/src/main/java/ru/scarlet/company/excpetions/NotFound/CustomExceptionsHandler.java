package ru.scarlet.company.excpetions.NotFound;

import jakarta.servlet.http.HttpServletRequest;
import java.time.Instant;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.scarlet.company.dtos.ErrorDetails;

@ControllerAdvice
public class CustomExceptionsHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CourseNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleCourseNotFoundException(HttpServletRequest request, CourseNotFoundException ex){
		return new ResponseEntity<>(new ErrorDetails(Instant.now().toEpochMilli(), request.getRequestURI(), ex.getMessage(), 404, MDC.get("CorrId")), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(DeanNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleDeanNotFoundException(HttpServletRequest request, DeanNotFoundException ex){
		return new ResponseEntity<>(new ErrorDetails(Instant.now().toEpochMilli(), request.getRequestURI(), ex.getMessage(), 404, MDC.get("CorrId")), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(ProfessorNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleProfessorNotFoundException(HttpServletRequest request, ProfessorNotFoundException ex){
		return new ResponseEntity<>(new ErrorDetails(Instant.now().toEpochMilli(), request.getRequestURI(), ex.getMessage(), 404, MDC.get("CorrId")), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(FacultyNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleFacultyNotFoundException(HttpServletRequest request, FacultyNotFoundException ex){
		return new ResponseEntity<>(new ErrorDetails(Instant.now().toEpochMilli(), request.getRequestURI(), ex.getMessage(), 404, MDC.get("CorrId")), HttpStatus.NOT_FOUND);
	}

}
