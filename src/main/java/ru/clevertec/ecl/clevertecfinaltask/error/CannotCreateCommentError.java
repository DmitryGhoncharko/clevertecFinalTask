package ru.clevertec.ecl.clevertecfinaltask.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
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
