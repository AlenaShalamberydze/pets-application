package com.leverx.pets.controller;

import com.leverx.pets.dto.request.UserCatDogRequest;
import com.leverx.pets.dto.response.AllEntitiesResponse;
import com.leverx.pets.dto.response.UserCatDogResponse;
import com.leverx.pets.provider.AuthProvider;
import com.leverx.pets.service.UserPetService;
import com.leverx.pets.service.UserPetServiceTransactionCoordinator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/proxy")
@RequiredArgsConstructor
public class UserPetController {

    private final UserPetService userPetService;
    private final UserPetServiceTransactionCoordinator serviceTransactionCoordinator;
    private final AuthProvider authProvider;

    @GetMapping
    public ResponseEntity<AllEntitiesResponse> getAll(
            @RequestHeader(value = "Authorization", defaultValue = "") String authHeader) {
        authProvider.setAuthHeader(authHeader);
        return ResponseEntity
                .ok(userPetService.getAll());
    }

    @PostMapping
    public ResponseEntity<UserCatDogResponse> saveUserCatDog(
            @RequestHeader(value = "Authorization", defaultValue = "") String authHeader,
            @RequestBody UserCatDogRequest entities) {
        authProvider.setAuthHeader(authHeader);
        return ResponseEntity
                .ok(serviceTransactionCoordinator.saveUserCatDog(entities));
    }

}
