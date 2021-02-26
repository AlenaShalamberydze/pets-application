package com.example.pets.service.impl;

import com.example.pets.dto.AnimalDto;
import com.example.pets.dto.DtoMapper;
import com.example.pets.exception.NotFoundException;
import com.example.pets.model.animal.Animal;
import com.example.pets.repository.AnimalRepository;
import com.example.pets.service.AnimalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;

    @Override
    public Animal getById(long id) {
        log.info("Getting animal from DB by id: {}", id);
        return animalRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Troubles getting animal with id: {} from DB: animal not found", id);
                    return new NotFoundException("Animal not found");
                });
    }

    @Override
    @Transactional
    public List<Animal> getAllByUserId(long userId) {
        log.info("Getting animals from DB by userId: {}", userId);
        return animalRepository.getAllByUserId(userId);
    }

    @Override
    public List<AnimalDto> getAll() {
        log.info("Getting all animals from DB");
        return animalRepository.findAll().stream()
                .map(DtoMapper::toDto)
                .collect(toList());
    }

    @Override
    @Transactional
    public void delete(long id) {
        log.info("Deleting animal from DB by id: {}", id);
        animalRepository.deleteById(id);
    }

    @Override
    public void save(Animal animal) {
        log.info("Saving animal into DB");
        animalRepository.save(animal);
    }

}
