package ru.clevertec.ecl.clevertecfinaltask.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
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
