package com.example.pets.controller;

import com.example.pets.dto.DtoMapper;
import com.example.pets.dto.response.ResponseAnimal;
import com.example.pets.dto.response.ResponseCat;
import com.example.pets.dto.response.ResponseDog;
import com.example.pets.model.user.User;
import com.example.pets.service.AnimalService;
import com.example.pets.service.CatService;
import com.example.pets.service.DogService;
import com.example.pets.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.OK;

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
    @ResponseStatus(OK)
    public void deleteById(@PathVariable long id) {
        userService.deleteById(id);
    }

    @GetMapping(value = "/{id}/cats")
    public ResponseEntity<List<ResponseCat>> findCatsByUserId(@PathVariable long id) {
        return ResponseEntity
                .ok(catService.getAllByUserId(id));
    }

    @GetMapping(value = "/{id}/dogs")
    public ResponseEntity<List<ResponseDog>> findDogsByUserId(@PathVariable long id) {
        return ResponseEntity
                .ok(dogService.getAllByUserId(id));
    }

    @GetMapping(value = "/{id}/animals")
    public ResponseEntity<List<ResponseAnimal>> getAnimalsByUserId(@PathVariable long id) {
        List<ResponseAnimal> animals = animalService.getAllByUserId(id).stream()
                .map(DtoMapper::toDto)
                .collect(toList());
        return ResponseEntity
                .ok(animals);
    }

}
