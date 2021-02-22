package com.example.pets.controller;

import com.example.pets.dto.AnimalDTO;
import com.example.pets.dto.CatDTO;
import com.example.pets.dto.DogDTO;
import com.example.pets.model.User;
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

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final DogService dogService;
    private final CatService catService;
    private final AnimalService animalService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findUserById(@PathVariable long id) {
        return ResponseEntity
                .ok()
                .body(userService.getUserById(id));
    }

    @GetMapping
    public ResponseEntity<List<User>> findAllUsers() {
        return ResponseEntity
                .ok()
                .body(userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return ResponseEntity
                .ok()
                .body(userService.saveUser(user));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> updateUserData(@PathVariable long id,
                                               @RequestBody User user) {
        user.setId(id);
        return ResponseEntity
                .ok()
                .body(userService.updateUser(user));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity.BodyBuilder deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return ResponseEntity
                .ok();
    }

    @GetMapping(value = "/{id}/cats")
    public ResponseEntity<List<CatDTO>> findCatsByUserId(@PathVariable long id) {
        return ResponseEntity
                .ok()
                .body(catService.getCatsByUserId(id));
    }

    @GetMapping(value = "/{id}/dogs")
    public ResponseEntity<List<DogDTO>> findDogsByUserId(@PathVariable long id) {
        return ResponseEntity
                .ok()
                .body(dogService.getDogsByUserId(id));
    }

    @GetMapping(value = "/{id}/animals")
    public ResponseEntity<List<AnimalDTO>> getAnimalsByUserId(@PathVariable long id) {
        return ResponseEntity
                .ok()
                .body(animalService.getAnimalsByUserId(id));
    }

}
