package com.leverx.pets.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@ControllerAdvice
public class UserPetExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    ResponseEntity<String> clientExceptionHandler(HttpClientErrorException e) {
        return ResponseEntity
                .status(e.getStatusCode())
                .body(e.getMessage());
    }

    @ExceptionHandler(HttpServerErrorException.class)
    ResponseEntity<String> serverExceptionHandler(HttpServerErrorException e){
        return ResponseEntity
                .status(e.getStatusCode())
                .body("Smth went wrong... try again later");
    }

}
