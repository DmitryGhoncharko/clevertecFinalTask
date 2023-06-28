package ru.clevertec.ecl.clevertecfinaltask.error;

public class CannotDeleteNewsError extends Error{
    public CannotDeleteNewsError() {
    }

    public CannotDeleteNewsError(String message) {
        super(message);
    }

    public CannotDeleteNewsError(String message, Throwable cause) {
        super(message, cause);
    }
}
