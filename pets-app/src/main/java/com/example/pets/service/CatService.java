package com.example.pets.service;

import com.example.pets.dto.CatDTO;
import com.example.pets.model.Cat;

import java.util.List;

public interface CatService {

    Cat getCatById(long id);

    List<CatDTO> getCatsByUserId(long userId);

    CatDTO saveCat(CatDTO cat);

    CatDTO updateCat(CatDTO catDTO);

    void deleteCat(long id);

}
