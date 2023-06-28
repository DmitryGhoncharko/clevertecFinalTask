package ru.clevertec.ecl.clevertecfinaltask.error;

public class CannotUpdateCommentError extends Error{
    public CannotUpdateCommentError() {
    }

    public CannotUpdateCommentError(String message) {
        super(message);
    }

    public CannotUpdateCommentError(String message, Throwable cause) {
        super(message, cause);
    }
}
