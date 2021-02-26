package com.leverx.pets.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class ProxyExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    ResponseEntity<String> unauthorizedExceptionHandler(HttpClientErrorException e){
        return ResponseEntity
                .status(e.getStatusCode())
                .build();
    }

}
