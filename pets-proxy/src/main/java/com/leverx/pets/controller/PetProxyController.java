package com.leverx.pets.controller;

import com.leverx.pets.dto.ResponseDto;
import com.leverx.pets.provider.AuthProvider;
import com.leverx.pets.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestMapping(value = "/proxy")
@RequiredArgsConstructor
public class PetProxyController {

    private final PetService petProxyService;
    private final AuthProvider authProvider;

    @GetMapping
    @RequestScope
    public ResponseEntity<ResponseDto> getUsersCatsDogs(
            @RequestHeader(value = "Authorization", defaultValue = "") String authHeader) {

        authProvider.setAuthHeader(authHeader);
        return ResponseEntity
                .ok(petProxyService.getUsersCatsDogs());
    }

}
