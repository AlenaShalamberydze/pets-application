package com.example.pets.service.impl;

import com.example.pets.dto.AnimalDTO;
import com.example.pets.dto.DTOMapper;
import com.example.pets.model.Animal;
import com.example.pets.repository.AnimalRepository;
import com.example.pets.repository.UserRepository;
import com.example.pets.service.AnimalService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Animal getAnimalById(long animalId) {
        return animalRepository.findById(animalId)
                .orElseThrow(() -> new RuntimeException("Animal not found"));
    }

    @Override
    @Transactional
    public List<AnimalDTO> getAnimalsByUserId(long userId) {
        return animalRepository.findAnimalsByUserId(userId).stream()
                .map(DTOMapper::toDto)
                .collect(toList());
    }
}
