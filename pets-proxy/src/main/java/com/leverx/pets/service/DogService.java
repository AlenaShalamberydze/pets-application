package com.leverx.pets.service;

import com.leverx.pets.model.dto.DogDto;

import java.util.Optional;

public interface DogService {

    Optional<DogDto> save(DogDto dog);

    void deleteById(long id);


}
