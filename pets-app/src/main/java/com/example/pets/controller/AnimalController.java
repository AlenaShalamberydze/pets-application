package com.example.pets.controller;

import com.example.pets.dto.AnimalDTO;
import com.example.pets.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.pets.dto.DTOMapper.toDto;

@RestController
@RequestMapping(value = "/animals")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<AnimalDTO> findById(@PathVariable long id) {
        return ResponseEntity
                .ok(toDto(animalService.getById(id)));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id) {
        animalService.delete(id);
        return ResponseEntity
                .ok()
                .build();
    }

    @GetMapping
    public ResponseEntity<List<AnimalDTO>> findAll() {
        return ResponseEntity
                .ok(animalService.getAll());
    }

}
