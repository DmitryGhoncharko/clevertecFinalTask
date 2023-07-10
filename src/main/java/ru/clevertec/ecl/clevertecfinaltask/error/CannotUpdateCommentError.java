package ru.clevertec.ecl.clevertecfinaltask.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
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
