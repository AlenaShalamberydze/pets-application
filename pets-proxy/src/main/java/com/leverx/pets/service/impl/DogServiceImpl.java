package com.leverx.pets.service.impl;

import com.leverx.pets.dto.request.DogRequest;
import com.leverx.pets.dto.response.DogResponse;
import com.leverx.pets.repository.DogRepository;
import com.leverx.pets.service.DogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DogServiceImpl implements DogService {

    private final DogRepository dogRepository;

    @Override
    @Transactional
    public DogResponse save(DogRequest dog) {
        return dogRepository.save(dog);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        dogRepository.deleteById(id);
    }

}
