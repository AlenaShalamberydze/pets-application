package com.example.pets.controller;

import com.example.pets.dto.AnimalDto;
import com.example.pets.dto.CatDto;
import com.example.pets.dto.DogDto;
import com.example.pets.dto.DtoMapper;
import com.example.pets.model.user.User;
import com.example.pets.service.AnimalService;
import com.example.pets.service.CatService;
import com.example.pets.service.DogService;
import com.example.pets.service.UserService;
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

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final DogService dogService;
    private final CatService catService;
    private final AnimalService animalService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable long id) {
        return ResponseEntity
                .ok(userService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity
                .ok(userService.getAll());
    }

    @PostMapping
    public ResponseEntity<User> add(@RequestBody User user) {
        return ResponseEntity
                .ok(userService.save(user));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@PathVariable long id,
                                       @RequestBody User user) {
        user.setId(id);
        return ResponseEntity
                .ok(userService.update(user));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id) {
        userService.deleteById(id);
        return ResponseEntity
                .ok()
                .build();
    }

    @GetMapping(value = "/{id}/cats")
    public ResponseEntity<List<CatDto>> findCatsByUserId(@PathVariable long id) {
        return ResponseEntity
                .ok(catService.getAllByUserId(id));
    }

    @GetMapping(value = "/{id}/dogs")
    public ResponseEntity<List<DogDto>> findDogsByUserId(@PathVariable long id) {
        return ResponseEntity
                .ok(dogService.getAllByUserId(id));
    }

    @GetMapping(value = "/{id}/animals")
    public ResponseEntity<List<AnimalDto>> getAnimalsByUserId(@PathVariable long id) {
        List<AnimalDto> animals = animalService.getAllByUserId(id).stream()
                .map(DtoMapper::toDto)
                .collect(toList());
        return ResponseEntity
                .ok(animals);
    }

}
