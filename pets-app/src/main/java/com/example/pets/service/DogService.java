package com.example.pets.service;

import com.example.pets.dto.DogDTO;
import com.example.pets.model.dog.Dog;

import java.util.List;

public interface DogService {

    Dog getById(long dogId);

    List<DogDTO> getAll();

    List<DogDTO> getAllByUserId(long userId);

    DogDTO save(DogDTO dogDTO);

    DogDTO update(DogDTO dogDTO);

    void deleteById(long dogId);

}
