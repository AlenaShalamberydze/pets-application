package com.leverx.pets.service.impl;

import com.leverx.pets.model.dto.CatDogUserDto;
import com.leverx.pets.model.dto.CatDto;
import com.leverx.pets.model.dto.DogDto;
import com.leverx.pets.model.user.User;
import com.leverx.pets.service.CatService;
import com.leverx.pets.service.DogService;
import com.leverx.pets.service.UserPetServiceTransactionCoordinator;
import com.leverx.pets.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserPetServiceTransactionCoordinatorImpl implements UserPetServiceTransactionCoordinator {

    private final UserService userService;
    private final CatService catService;
    private final DogService dogService;

    @Override
    public CatDogUserDto saveCatDogUser(CatDogUserDto catDogUserDto) {
        log.info("Transactional Coordinator: saving user+cat+dog into pets-app server DB");

        User user = userService.save(catDogUserDto.getUser())
                .orElseThrow(() -> {
                    log.error("Troubles saving user into DB");
                    return new HttpClientErrorException(BAD_REQUEST, "failed to save user into DB");
                });

        CatDto cat = catService.save(catDogUserDto.getCat())
                .orElseThrow(() -> {
                    log.error("Troubles saving cat into DB");
                    userService.deleteById(user.getId());
                    return new HttpClientErrorException(BAD_REQUEST, "failed to save cat into DB");
                });

        DogDto dog = dogService.save(catDogUserDto.getDog())
                .orElseThrow(() -> {
                    log.error("Troubles saving dog into DB");
                   catService.deleteById(cat.getId());
                   userService.deleteById(user.getId());
                   return new HttpClientErrorException(BAD_REQUEST, "failed to save dog into DB");
                });

        return new CatDogUserDto(cat, dog, user);
    }
}
