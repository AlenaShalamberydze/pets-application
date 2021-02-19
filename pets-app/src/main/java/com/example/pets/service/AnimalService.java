package com.example.pets.service;

import com.example.pets.dto.AnimalDTO;
import com.example.pets.model.Animal;

import java.util.List;

public interface AnimalService {

    Animal getAnimalById(long animalId);

    List<AnimalDTO> getAnimalsByUserId(long userId);


}
