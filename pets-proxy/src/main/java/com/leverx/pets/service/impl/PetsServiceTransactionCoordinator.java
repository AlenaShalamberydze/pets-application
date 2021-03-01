package com.leverx.pets.service.impl;

import com.leverx.pets.dto.CatDogUserDto;
import com.leverx.pets.dto.CatDto;
import com.leverx.pets.dto.DogDto;
import com.leverx.pets.exception.NotFoundException;
import com.leverx.pets.model.user.User;
import com.leverx.pets.service.PetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PetsServiceTransactionCoordinator {

    private final PetService petService;

    public CatDogUserDto saveCatDogUser(CatDogUserDto userCatDogDto) {
        log.info("Transactional Coordinator: saving user+cat+dog into pets-app service DB");

        User user = petService.saveUser(userCatDogDto.getUser())
                .orElseThrow(() -> {
                    log.error("Troubles saving user into DB");
                    return new NotFoundException("user not found: failed to save into DB");
                });
        CatDto cat = petService.saveCat(userCatDogDto.getCat())
                .orElseThrow(() -> {
                    log.error("Troubles saving cat into DB");
                    return new NotFoundException("Cat not found: failed to save into DB");
                });
        DogDto dog = petService.saveDog(userCatDogDto.getDog())
                .orElseThrow(() -> {
                    log.error("Troubles saving dog into DB");
                    return new NotFoundException("Dog not found: failed to save into DB");
                });

        return new CatDogUserDto(cat, dog, user);

    }
}
