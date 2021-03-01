package com.leverx.pets.service;

import com.leverx.pets.model.dto.CatDogUserDto;

public interface UserPetServiceTransactionCoordinator {

    CatDogUserDto saveCatDogUser(CatDogUserDto userCatDogDto);
}
