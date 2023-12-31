package ru.scarlet.company.excpetions.Null;

public class HeaderNullException extends RuntimeException{
    public HeaderNullException() {
    }

    public HeaderNullException(String message) {
        super(message);
    }

    public HeaderNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public HeaderNullException(Throwable cause) {
        super(cause);
    }

    public HeaderNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
