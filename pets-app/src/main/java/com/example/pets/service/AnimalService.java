package com.example.pets.service;

import com.example.pets.dto.AnimalDTO;
import com.example.pets.model.animal.Animal;

import java.util.List;

public interface AnimalService {

    Animal getById(long animalId);

    List<Animal> getAllByUserId(long userId);

    List<AnimalDTO> getAll();

    void delete(long id);

    void save(Animal animal);

}
