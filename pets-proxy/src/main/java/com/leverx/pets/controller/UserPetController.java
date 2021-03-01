package com.leverx.pets.controller;

import com.leverx.pets.model.dto.AllEntitiesDto;
import com.leverx.pets.model.dto.CatDogUserDto;
import com.leverx.pets.provider.AuthProvider;
import com.leverx.pets.service.UserPetService;
import com.leverx.pets.service.impl.UserPetServiceTransactionCoordinatorImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestMapping(value = "/proxy")
@RequiredArgsConstructor
public class UserPetController {

    private final UserPetService userPetService;
    private final UserPetServiceTransactionCoordinatorImpl serviceTransactionCoordinator;
    private final AuthProvider authProvider;

    @GetMapping
    @RequestScope
    public ResponseEntity<AllEntitiesDto> getAll(
            @RequestHeader(value = "Authorization", defaultValue = "") String authHeader) {

        authProvider.setAuthHeader(authHeader);
        return ResponseEntity
                .ok(userPetService.getAll());
    }

    @PostMapping
    @RequestScope
    public ResponseEntity<CatDogUserDto> saveUserCatDog(
            @RequestHeader(value = "Authorization", defaultValue = "") String authHeader,
            @RequestBody CatDogUserDto entities) {

        authProvider.setAuthHeader(authHeader);
        return ResponseEntity
                .ok(serviceTransactionCoordinator.saveCatDogUser(entities));
    }

}
