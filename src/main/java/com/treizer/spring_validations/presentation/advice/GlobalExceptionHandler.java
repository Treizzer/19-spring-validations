package com.treizer.spring_validations.presentation.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /*
     * Collect the errors marked by @Valid and transfer to Map
     * Return the errors in the Map using ResponseEntity.badRequest().body()
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleInvalidArguments(MethodArgumentNotValidException exception) {
        // var errors = exception.getBindingResult()
        // .getFieldErrors()
        // .stream()
        // .collect(Collectors.toMap(FieldError::getField,
        // FieldError::getDefaultMessage));
        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult().getFieldErrors()
                .forEach(e -> errors.put(e.getField(), e.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFoundException(NotFoundException exception) {
        Map<String, String> errors = new HashMap<>();
        errors.put(exception.getLocalizedMessage(), exception.getMessage());
        errors.put("Error: ", exception.toString());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleException(Exception exception) {
        // Map<String, String> errors = new HashMap<>();
        // errors.put(exception.getLocalizedMessage(), exception.getMessage());
        // errors.put("Error", exception.toString());
        // return ResponseEntity.internalServerError().body(errors);

        return ResponseEntity.internalServerError()
                .body(Map.of("Error", exception.toString()));
    }

    // @ExceptionHandler(MethodArgumentNotValidException.class)
    // public Map<String, String>
    // handleInvalidArguments(MethodArgumentNotValidException ex) {
    // Map<String, String> errors = new HashMap<>();

    // ex.getBindingResult().getFieldErrors()
    // .forEach(error -> {
    // errors.put(error.getField(), error.getDefaultMessage());
    // });

    // return errors;
    // }

}
