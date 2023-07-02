package ru.clevertec.ecl.clevertecfinaltask.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)

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
