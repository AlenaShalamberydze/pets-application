package com.leverx.pets.service;

import com.leverx.pets.dto.request.DogRequest;
import com.leverx.pets.dto.response.DogResponse;

public interface DogService {

    DogResponse save(DogRequest dog);

    void deleteById(long id);


}
