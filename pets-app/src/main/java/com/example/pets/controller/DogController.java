package com.example.pets.controller;

import com.example.pets.dto.DogDTO;
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

import static com.example.pets.dto.DTOMapper.toDogDto;

@RestController
@RequestMapping(value = "/dogs")
@RequiredArgsConstructor
public class DogController {

    private final DogService dogService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<DogDTO> findDogById(@PathVariable long id) {
        return ResponseEntity
                .ok()
                .body(toDogDto(dogService.getDogById(id)));
    }

    @PostMapping
    public ResponseEntity<DogDTO> addDog(@RequestBody DogDTO dogDTO) {
        return ResponseEntity
                .ok()
                .body(dogService.saveDog(dogDTO));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity.BodyBuilder updateDogInfo(@PathVariable long id,
                                                    @RequestBody DogDTO dogDTO) {
        dogDTO.setId(id);
        dogService.updateDog(dogDTO);
        return ResponseEntity
                .ok();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity.BodyBuilder deleteDog(@PathVariable long id) {
        dogService.deleteDog(id);
        return ResponseEntity
                .ok();
    }


}
