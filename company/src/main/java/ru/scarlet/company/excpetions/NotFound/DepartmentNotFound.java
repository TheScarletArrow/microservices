package ru.scarlet.company.excpetions.NotFound;

public class DepartmentNotFound extends RuntimeException {
	public DepartmentNotFound() {
	}

	public DepartmentNotFound(String message) {
		super(message);
	}

	public DepartmentNotFound(String message, Throwable cause) {
		super(message, cause);
	}

	public DepartmentNotFound(Throwable cause) {
		super(cause);
	}

	public DepartmentNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
