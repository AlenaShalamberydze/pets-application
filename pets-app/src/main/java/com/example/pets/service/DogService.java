package com.example.pets.service;

import com.example.pets.dto.DogDTO;
import com.example.pets.model.Dog;

import java.util.List;

public interface DogService {

    Dog getDogById(long dogId);

    List<DogDTO> getDogsByUserId(long userId);

    DogDTO saveDog(Dog dog, long userId);

    DogDTO updateDog(Dog dog, long userId, long dogId);

    void deleteDog(long dogId);

}
