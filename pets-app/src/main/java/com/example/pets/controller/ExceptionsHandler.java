package com.example.pets.controller;

import com.example.pets.exception.NotFoundException;
import com.example.pets.exception.UserAlreadyExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<String> notFoundExceptionHandler(NotFoundException e) {
        return ResponseEntity
                .status(NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    ResponseEntity<String> userAlreadyExistsExceptionHandler(UserAlreadyExistsException e) {
        return ResponseEntity
                .status(CONFLICT)
                .body(e.getMessage());
    }

}
