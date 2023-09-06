package ru.scarlet.company.excpetions.NotFound;

public class ProfessorNotFoundException extends RuntimeException{
	public ProfessorNotFoundException() {
	}

	public ProfessorNotFoundException(String message) {
		super(message);
	}

	public ProfessorNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProfessorNotFoundException(Throwable cause) {
		super(cause);
	}

	public ProfessorNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
