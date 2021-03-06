package com.example.pets.controller;

import com.example.pets.dto.CatDto;
import com.example.pets.service.CatService;
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

import static com.example.pets.dto.DtoMapper.toCatDto;

@RestController
@RequestMapping(value = "/cats")
@RequiredArgsConstructor
public class CatController {

    private final CatService catService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<CatDto> findById(@PathVariable long id) {
        return ResponseEntity
                .ok(toCatDto(catService.getById(id)));
    }

    @GetMapping
    public ResponseEntity<List<CatDto>> findAll() {
        return ResponseEntity
                .ok(catService.getAll());
    }

    @PostMapping
    public ResponseEntity<CatDto> add(@RequestBody CatDto catDTO) {
        return ResponseEntity
                .ok(catService.save(catDTO));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@PathVariable long id,
                                         @RequestBody CatDto catDTO) {
        catDTO.setId(id);
        catService.update(catDTO);
        return ResponseEntity
                .ok()
                .build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id) {
        catService.deleteById(id);
        return ResponseEntity
                .ok()
                .build();
    }

}
