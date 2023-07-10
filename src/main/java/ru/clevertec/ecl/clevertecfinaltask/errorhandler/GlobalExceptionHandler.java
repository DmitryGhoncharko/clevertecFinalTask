package ru.clevertec.ecl.clevertecfinaltask.errorhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.clevertec.ecl.clevertecfinaltask.error.CannotCreateCommentError;
import ru.clevertec.ecl.clevertecfinaltask.error.CannotDeleteCommentError;
import ru.clevertec.ecl.clevertecfinaltask.error.CannotDeleteNewsError;
import ru.clevertec.ecl.clevertecfinaltask.error.CannotUpdateCommentError;
import ru.clevertec.ecl.clevertecfinaltask.error.CannotUpdateNewsError;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({CannotDeleteCommentError.class, CannotDeleteNewsError.class})
    public ResponseEntity<ErrorResponse> handleApiException(Error e) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({CannotCreateCommentError.class, CannotUpdateCommentError.class, CannotUpdateNewsError.class})
    public ResponseEntity<String> handleException(Error e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .toList();
        return ResponseEntity.badRequest().body(errors.toString());
    }

}
