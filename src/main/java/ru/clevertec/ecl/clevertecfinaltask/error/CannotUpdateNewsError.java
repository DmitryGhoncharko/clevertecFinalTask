package ru.clevertec.ecl.clevertecfinaltask.error;

public class CannotUpdateNewsError extends Error{
    public CannotUpdateNewsError() {
    }

    public CannotUpdateNewsError(String message) {
        super(message);
    }

    public CannotUpdateNewsError(String message, Throwable cause) {
        super(message, cause);
    }
}
