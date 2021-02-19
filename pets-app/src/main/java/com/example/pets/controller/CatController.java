package com.example.pets.controller;

import com.example.pets.dto.CatDTO;
import com.example.pets.model.Cat;
import com.example.pets.service.CatService;
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

import static com.example.pets.dto.DTOMapper.toCatDto;

@RestController
@RequestMapping(value = "/cat")
@AllArgsConstructor
public class CatController {

    private final CatService catService;

    @GetMapping(value = "/{catId}")
    public ResponseEntity<CatDTO> findCatById(@PathVariable long catId) {
        return new ResponseEntity<>(
                toCatDto(catService.getCatById(catId)),
                HttpStatus.OK);
    }

    @GetMapping(value = "/all/{catId}")
    public List<CatDTO> findCatsByUserId(@PathVariable long catId) {
        return catService.getCatsByUserId(catId);
    }

    @PostMapping
    public CatDTO addCat(@RequestParam(value = "userId") long userId,
                         @RequestBody Cat cat) {
        return catService.saveCat(cat, userId);
    }

    @PutMapping(value = "/{catId}")
    public ResponseEntity<String> updateCatInfo(@PathVariable long catId,
                                         @RequestParam(value = "userId") long userId,
                                         @RequestBody Cat cat
    ) {
        catService.updateCat(cat, userId, catId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{catId}")
    public ResponseEntity<String> deleteCat(@PathVariable long catId) {
        catService.deleteCat(catId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
