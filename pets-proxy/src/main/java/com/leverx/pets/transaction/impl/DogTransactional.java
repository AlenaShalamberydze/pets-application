package com.leverx.pets.transaction.impl;

import com.leverx.pets.dto.request.DogRequest;
import com.leverx.pets.dto.response.DogResponse;
import com.leverx.pets.service.DogService;
import com.leverx.pets.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class DogTransactional implements Transactional {

    private final DogRequest dog;
    private final DogService dogService;
    private DogResponse dogResponse;

    @Override
    public DogResponse save() {
        dogResponse = dogService.save(dog);
        return dogResponse;
    }

    @Override
    public void delete() {
        dogService.deleteById(dogResponse.getId());
    }

}
