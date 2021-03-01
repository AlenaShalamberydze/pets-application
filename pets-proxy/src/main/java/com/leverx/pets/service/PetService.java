package com.leverx.pets.service;

import com.leverx.pets.dto.AllEntitiesDto;
import com.leverx.pets.dto.CatDto;
import com.leverx.pets.dto.DogDto;
import com.leverx.pets.model.user.User;

import java.util.Optional;

public interface PetService {

    AllEntitiesDto getUsersCatsDogs();

    Optional<User> saveUser(User user);

    Optional<CatDto> saveCat(CatDto cat);

    Optional<DogDto> saveDog(DogDto dog);
}
