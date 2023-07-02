package ru.clevertec.ecl.clevertecfinaltask.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
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
