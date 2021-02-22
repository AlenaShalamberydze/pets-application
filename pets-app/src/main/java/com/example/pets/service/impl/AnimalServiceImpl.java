package com.example.pets.service.impl;

import com.example.pets.dto.AnimalDTO;
import com.example.pets.dto.DTOMapper;
import com.example.pets.exception.NotFoundException;
import com.example.pets.model.Animal;
import com.example.pets.repository.AnimalRepository;
import com.example.pets.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;

    @Transactional
    public Animal getAnimalById(long id) {
        return animalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cat not found"));
    }

    @Override
    @Transactional
    public List<AnimalDTO> getAnimalsByUserId(long userId) {
        return animalRepository.getAllByUserId(userId).stream()
                .map(DTOMapper::toDto)
                .collect(toList());
    }

    @Override
    @Transactional
    public void deleteAnimal(long id) {
        animalRepository.deleteById(id);
    }

}
