package ru.clevertec.ecl.clevertecfinaltask.error;

public class CannotDeleteCommentError extends Error{
    public CannotDeleteCommentError() {
    }

    public CannotDeleteCommentError(String message) {
        super(message);
    }

    public CannotDeleteCommentError(String message, Throwable cause) {
        super(message, cause);
    }
}
