package com.leverx.pets.service.impl;

import com.leverx.pets.model.dto.DogDto;
import com.leverx.pets.repository.DogRepository;
import com.leverx.pets.service.DogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DogServiceImpl implements DogService {

    private final DogRepository dogRepository;

    @Override
    @Transactional
    public Optional<DogDto> save(DogDto dog) {
        return dogRepository.save(dog);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        dogRepository.deleteById(id);
    }

}
