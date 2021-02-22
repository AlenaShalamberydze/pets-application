package com.example.pets.service;

import com.example.pets.dto.DogDTO;
import com.example.pets.model.Dog;

import java.util.List;

public interface DogService {

    Dog getDogById(long dogId);

    List<DogDTO> getDogsByUserId(long userId);

    DogDTO saveDog(DogDTO dogDTO);

    DogDTO updateDog(DogDTO dogDTO);

    void deleteDog(long dogId);

}
