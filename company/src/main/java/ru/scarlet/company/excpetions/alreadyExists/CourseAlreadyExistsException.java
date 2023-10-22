package ru.scarlet.company.excpetions.alreadyExists;

public class CourseAlreadyExistsException extends RuntimeException{
    public CourseAlreadyExistsException() {
        super();
    }

    public CourseAlreadyExistsException(String message) {
        super(message);
    }

    public CourseAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public CourseAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    protected CourseAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
