package com.example.pets.controller;

import com.example.pets.dto.request.RequestDog;
import com.example.pets.dto.response.ResponseDog;
import com.example.pets.service.DogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.pets.dto.DtoMapper.toDogDto;
import static org.springframework.http.HttpStatus.OK;

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
    @ResponseStatus(OK)
    public void update(@PathVariable long id,
                                         @RequestBody RequestDog requestDog) {
        dogService.update(requestDog, id);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(OK)
    public void deleteById(@PathVariable long id) {
        dogService.deleteById(id);
    }

}
