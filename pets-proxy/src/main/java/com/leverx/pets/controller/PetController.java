package com.leverx.pets.controller;

import com.leverx.pets.dto.AllEntitiesDto;
import com.leverx.pets.dto.CatDogUserDto;
import com.leverx.pets.provider.AuthProvider;
import com.leverx.pets.service.PetService;
import com.leverx.pets.service.impl.PetsServiceTransactionCoordinator;
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
public class PetController {

    private final PetService petProxyService;
    private final PetsServiceTransactionCoordinator petsServiceTransactionCoordinator;
    private final AuthProvider authProvider;

    @GetMapping
    @RequestScope
    public ResponseEntity<AllEntitiesDto> getUsersCatsDogs(
            @RequestHeader(value = "Authorization", defaultValue = "") String authHeader) {

        authProvider.setAuthHeader(authHeader);
        return ResponseEntity
                .ok(petProxyService.getUsersCatsDogs());
    }

    @PostMapping
    @RequestScope
    public ResponseEntity<CatDogUserDto> saveUserCatDog(
            @RequestHeader(value = "Authorization", defaultValue = "") String authHeader,
            @RequestBody CatDogUserDto entities) {

        authProvider.setAuthHeader(authHeader);
        return ResponseEntity
                .ok(petsServiceTransactionCoordinator.saveCatDogUser(entities));
    }

}
