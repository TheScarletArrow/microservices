package ru.scarlet.company.excpetions.BadRequest;

public class BadRequestExceprion extends RuntimeException{
    public BadRequestExceprion() {
    }

    public BadRequestExceprion(String message) {
        super(message);
    }

    public BadRequestExceprion(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestExceprion(Throwable cause) {
        super(cause);
    }

    public BadRequestExceprion(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
