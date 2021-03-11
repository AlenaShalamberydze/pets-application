package com.leverx.pets.controller;

import com.leverx.pets.RepositoryException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;

@ControllerAdvice
public class UserPetExceptionHandler {

    @ExceptionHandler({HttpClientErrorException.class, HttpServerErrorException.class})
    ResponseEntity<String> clientExceptionHandler(HttpStatusCodeException e) {
        return ResponseEntity
                .status(e.getStatusCode())
                .body(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<String> runtimeExceptionHandler(RuntimeException e) {
        return ResponseEntity
                .status(0)
                .body(e.getMessage());
    }

    @ExceptionHandler(RepositoryException.class)
    ResponseEntity<String> repositoryExceptionHandler(RepositoryException e) {
        return ResponseEntity
                .status(0)
                .body(e.getMessage());
    }

}
