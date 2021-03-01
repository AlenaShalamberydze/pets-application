package com.leverx.pets.repository;

import com.leverx.pets.dto.DogDto;

import java.util.List;
import java.util.Optional;

public interface DogRepository {

    List<DogDto> getDogs();

    Optional<DogDto> saveDog(DogDto dog);
}
