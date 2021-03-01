package com.example.pets.service;

import com.example.pets.dto.DogDto;
import com.example.pets.model.dog.Dog;

import java.util.List;

public interface DogService {

    Dog getById(long dogId);

    List<DogDto> getAll();

    List<DogDto> getAllByUserId(long userId);

    DogDto save(DogDto dogDTO);

    DogDto update(DogDto dogDTO);

    void deleteById(long dogId);

}
