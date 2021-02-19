package com.example.pets.controller;

import com.example.pets.dto.AnimalDTO;
import com.example.pets.service.AnimalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class AnimalController {

    private final AnimalService animalService;

    @GetMapping(value = "/animals/{id}")
    public List<AnimalDTO> getAnimalsByUserId(@PathVariable long id) {
        return animalService.getAnimalsByUserId(id);
    }

}
