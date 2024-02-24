package ru.scarlet.company.excpetions;

import feign.FeignException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.scarlet.company.dtos.ErrorDetails;
import ru.scarlet.company.excpetions.BadRequest.BadRequestExceprion;
import ru.scarlet.company.excpetions.NotFound.*;
import ru.scarlet.company.excpetions.Null.HeaderNullException;
import ru.scarlet.company.excpetions.alreadyExists.CourseAlreadyExistsException;

import java.time.Instant;

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
	@ExceptionHandler(ExpertiseNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleExpertiseNotFoundException(HttpServletRequest request, ExpertiseNotFoundException ex){
		return new ResponseEntity<>(new ErrorDetails(Instant.now().toEpochMilli(), request.getRequestURI(), ex.getMessage(), 404, MDC.get("CorrId")), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(CourseAlreadyExistsException.class)
	public ResponseEntity<ErrorDetails> handleCourseAlreadyExistsException(HttpServletRequest request, CourseAlreadyExistsException ex){
		return new ResponseEntity<>(new ErrorDetails(Instant.now().toEpochMilli(), request.getRequestURI(), ex.getMessage(), 400, MDC.get("CorrId")), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HeaderNullException.class)
	public ResponseEntity<ErrorDetails> handleHeaderNullException(HttpServletRequest request, HeaderNullException ex){
		return new ResponseEntity<>(new ErrorDetails(Instant.now().toEpochMilli(), request.getRequestURI(), ex.getMessage(), 400, MDC.get("CorrId")), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BadRequestExceprion.class)
	public ResponseEntity<ErrorDetails> handleBadRequestExceprion(HttpServletRequest request, BadRequestExceprion ex){
		return new ResponseEntity<>(new ErrorDetails(Instant.now().toEpochMilli(), request.getRequestURI(), ex.getMessage(), 400, MDC.get("CorrId")), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(FeignException.class)
	public ResponseEntity<ErrorDetails> handleFeignException(HttpServletRequest request, FeignException ex){
		return new ResponseEntity<>(new ErrorDetails(Instant.now().toEpochMilli(), request.getRequestURI(), ex.getLocalizedMessage(), 400, MDC.get("CorrId")), HttpStatus.BAD_REQUEST);
	}
}
