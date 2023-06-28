package ru.clevertec.ecl.clevertecfinaltask.error;

public class CannotCreateCommentError extends Error{
    public CannotCreateCommentError() {
    }

    public CannotCreateCommentError(String message) {
        super(message);
    }

    public CannotCreateCommentError(String message, Throwable cause) {
        super(message, cause);
    }
}
