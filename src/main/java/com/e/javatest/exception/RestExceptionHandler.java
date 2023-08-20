package com.e.javatest.exception;

import com.e.javatest.response.ErrorResponse;
import com.e.javatest.response.ValidationErrorResponse;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ValidationErrorResponse handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex, WebRequest request) {
        List<String> errors =
                ex.getBindingResult().getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList());
        ;
        String message = "Erro ao validar requisição de entrada";
        ValidationErrorResponse errorMessage = new ValidationErrorResponse(message, errors);
        return errorMessage;
    }

    @ExceptionHandler(
            value = {
                DuplicateEntryException.class,
                InvalidIdException.class,
                EntryStillBeingUsedException.class,
                NoFieldToUpdateException.class
            })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleSimpleBadRequestExceptions(Exception ex, WebRequest request) {
        ErrorResponse errorMessage = new ErrorResponse(ex);
        return errorMessage;
    }

    @ExceptionHandler(value = {EntryNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorResponse handleEntryNotFoundException(
            EntryNotFoundException ex, WebRequest request) {
        ErrorResponse errorMessage = new ErrorResponse(ex);
        return errorMessage;
    }

    // temp
    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>(
                "not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
