package com.example.pets.service;

import com.example.pets.dto.response.ResponseAnimal;
import com.example.pets.model.animal.Animal;

import java.util.List;

public interface AnimalService {

    Animal getById(long animalId);

    List<Animal> getAllByUserId(long userId);

    List<ResponseAnimal> getAll();

    void delete(long id);

    void save(Animal animal);

}
