package com.example.pets.controller;

import com.example.pets.dto.DogDTO;
import com.example.pets.model.Dog;
import com.example.pets.service.DogService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.pets.dto.DTOMapper.toDogDto;

@RestController
@RequestMapping(value = "/dog")
@AllArgsConstructor
public class DogController {

    private final DogService dogService;

    @GetMapping(value = "/{dogId}")
    public ResponseEntity<DogDTO> findDogById(@PathVariable long dogId) {
        return new ResponseEntity<>(toDogDto(dogService.getDogById(dogId)), HttpStatus.OK);
    }

    @GetMapping(value = "/all/{userId}")
    public List<DogDTO> findDogsByUserId(@PathVariable long userId) {
        return dogService.getDogsByUserId(userId);
    }

    @PostMapping
    public DogDTO addDog(@RequestParam(value = "userId") long userId,
                         @RequestBody Dog dog) {
        return dogService.saveDog(dog, userId);
    }

    @PutMapping(value = "/{dogId}")
    public ResponseEntity<String> updateDog(@PathVariable long dogId,
                                         @RequestParam(value = "userId") long userId,
                                         @RequestBody Dog dog
    ) {
        dogService.updateDog(dog, userId, dogId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{dogId}")
    public ResponseEntity<String> deleteDog(@PathVariable long dogId) {
        dogService.deleteDog(dogId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
