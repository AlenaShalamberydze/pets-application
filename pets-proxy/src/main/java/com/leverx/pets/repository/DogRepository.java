package com.leverx.pets.repository;

import com.leverx.pets.model.dto.DogDto;

import java.util.List;
import java.util.Optional;

public interface DogRepository {

    List<DogDto> getAll();

    Optional<DogDto> save(DogDto dog);

    void deleteById(long id);
}
