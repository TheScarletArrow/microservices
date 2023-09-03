package ru.scarlet.company.excpetions.NotFound;

public class DeanNotFoundException extends RuntimeException {
	public DeanNotFoundException() {
	}

	public DeanNotFoundException(String message) {
		super(message);
	}

	public DeanNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public DeanNotFoundException(Throwable cause) {
		super(cause);
	}

	public DeanNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
