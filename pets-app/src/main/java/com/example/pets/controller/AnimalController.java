package com.example.pets.controller;

import com.example.pets.dto.response.ResponseAnimal;
import com.example.pets.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.pets.dto.DtoMapper.toDto;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/animals")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseAnimal> findById(@PathVariable long id) {
        return ResponseEntity
                .ok(toDto(animalService.getById(id)));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(OK)
    public void deleteById(@PathVariable long id) {
        animalService.delete(id);
    }

    @GetMapping
    public ResponseEntity<List<ResponseAnimal>> findAll() {
        return ResponseEntity
                .ok(animalService.getAll());
    }

}
