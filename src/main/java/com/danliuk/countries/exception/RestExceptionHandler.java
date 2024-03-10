package com.danliuk.countries.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<ExceptionBody> handleItemNotFoundException(ItemNotFoundException e) {
        ExceptionBody body = new ExceptionBody(HttpStatus.NOT_FOUND, e.getMessage());
        return new ResponseEntity<>(body, HttpStatusCode.valueOf(404));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionBody> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        ExceptionBody body = new ExceptionBody(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        return new ResponseEntity<>(body, HttpStatusCode.valueOf(404));
    }
}
