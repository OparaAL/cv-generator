package com.application.cvgenerator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseException itemNotFoundException(ItemNotFoundException ex){
        return new ResponseException(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(WrongUserDataException.class)
    public ResponseException wrongUserDataException(WrongUserDataException ex){
        return new ResponseException(ex.getMessage());
    }
}
