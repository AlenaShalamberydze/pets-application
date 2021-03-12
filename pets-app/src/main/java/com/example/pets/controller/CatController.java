package com.example.pets.controller;

import com.example.pets.dto.request.RequestCat;
import com.example.pets.dto.response.ResponseCat;
import com.example.pets.service.CatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.pets.dto.DtoMapper.toCatDto;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/cats")
@RequiredArgsConstructor
public class CatController {

    private final CatService catService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseCat> findById(@PathVariable long id) {
        return ResponseEntity
                .ok(toCatDto(catService.getById(id)));
    }

    @GetMapping
    public ResponseEntity<List<ResponseCat>> findAll() {
        return ResponseEntity
                .ok(catService.getAll());
    }

    @PostMapping
    public ResponseEntity<ResponseCat> add(@RequestBody RequestCat requestCat) {
        return ResponseEntity
                .ok(catService.save(requestCat));
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(OK)
    public void update(@PathVariable long id,
                                         @RequestBody RequestCat requestCat) {
        catService.update(requestCat, id);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(OK)
    public void deleteById(@PathVariable long id) {
        catService.deleteById(id);
    }

}
