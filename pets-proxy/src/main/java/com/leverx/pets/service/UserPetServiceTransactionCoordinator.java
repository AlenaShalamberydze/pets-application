package com.leverx.pets.service;

import com.leverx.pets.dto.request.UserCatDogRequest;
import com.leverx.pets.dto.response.UserCatDogResponse;

public interface UserPetServiceTransactionCoordinator {

    UserCatDogResponse saveUserCatDog(UserCatDogRequest userCatDog);
}
