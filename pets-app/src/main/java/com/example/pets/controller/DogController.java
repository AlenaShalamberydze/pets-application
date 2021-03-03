package com.example.pets.controller;

import com.example.pets.dto.request.RequestDog;
import com.example.pets.dto.response.ResponseDog;
import com.example.pets.model.dog.Dog;
import com.example.pets.service.DogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.pets.dto.DtoMapper.fromDogDto;
import static com.example.pets.dto.DtoMapper.toDogDto;

@RestController
@RequestMapping(value = "/dogs")
@RequiredArgsConstructor
public class DogController {

    private final DogService dogService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseDog> findById(@PathVariable long id) {
        return ResponseEntity
                .ok(toDogDto(dogService.getById(id)));
    }

    @PostMapping
    public ResponseEntity<ResponseDog> add(@RequestBody RequestDog requestDog) {
        return ResponseEntity
                .ok(dogService.save(requestDog));
    }

    @GetMapping
    public ResponseEntity<List<ResponseDog>> findAll() {
        return ResponseEntity
                .ok(dogService.getAll());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@PathVariable long id,
                                             @RequestBody RequestDog requestDog) {
        dogService.update(requestDog, id);
        return ResponseEntity
                .ok()
                .build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id) {
        dogService.deleteById(id);
        return ResponseEntity
                .ok()
                .build();
    }

}
