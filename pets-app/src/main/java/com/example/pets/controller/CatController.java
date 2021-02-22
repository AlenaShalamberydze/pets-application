package com.example.pets.controller;

import com.example.pets.dto.CatDTO;
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

import static com.example.pets.dto.DTOMapper.toCatDto;

@RestController
@RequestMapping(value = "/cats")
@RequiredArgsConstructor
public class CatController {

    private final CatService catService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<CatDTO> findCatById(@PathVariable long id) {
        return ResponseEntity
                .ok()
                .body(toCatDto(catService.getCatById(id)));
    }

    @PostMapping
    public ResponseEntity<CatDTO> addCat(@RequestBody CatDTO catDTO) {
        return ResponseEntity
                .ok()
                .body(catService.saveCat(catDTO));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity.BodyBuilder updateCatInfo(@PathVariable long id,
                                                    @RequestBody CatDTO catDTO) {
        catDTO.setId(id);
        catService.updateCat(catDTO);
        return ResponseEntity
                .ok();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity.BodyBuilder deleteCat(@PathVariable long id) {
        catService.deleteCat(id);
        return ResponseEntity
                .ok();
    }

}
