package com.leverx.pets.repository;

import com.leverx.pets.dto.request.DogRequest;
import com.leverx.pets.dto.response.DogResponse;

import java.util.List;

public interface DogRepository {

    List<DogResponse> getAll();

    DogResponse save(DogRequest dog);

    void deleteById(long id);
}
