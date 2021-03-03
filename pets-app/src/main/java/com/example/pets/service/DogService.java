package com.example.pets.service;

import com.example.pets.dto.request.RequestDog;
import com.example.pets.dto.response.ResponseDog;
import com.example.pets.model.dog.Dog;

import java.util.List;

public interface DogService {

    Dog getById(long dogId);

    List<ResponseDog> getAll();

    List<ResponseDog> getAllByUserId(long userId);

    ResponseDog save(RequestDog requestDog);

    ResponseDog update(RequestDog requestDog, long id);

    void deleteById(long dogId);

}
