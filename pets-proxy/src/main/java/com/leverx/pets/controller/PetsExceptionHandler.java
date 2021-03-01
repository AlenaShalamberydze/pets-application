package com.leverx.pets.controller;

import com.leverx.pets.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class PetsExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    ResponseEntity<String> clientExceptionHandler(HttpClientErrorException e){
        return ResponseEntity
                .status(e.getStatusCode())
                .build();
    }

    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<String> notFoundExceptionHandler(NotFoundException e) {
        return ResponseEntity
                .status(NOT_FOUND)
                .body(e.getMessage());
    }

}
