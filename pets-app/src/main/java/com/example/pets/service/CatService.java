package com.example.pets.service;

import com.example.pets.dto.CatDTO;
import com.example.pets.model.cat.Cat;

import java.util.List;

public interface CatService {

    Cat getById(long id);

    List<CatDTO> getAll();

    List<CatDTO> getAllByUserId(long userId);

    CatDTO save(CatDTO cat);

    CatDTO update(CatDTO catDTO);

    void deleteById(long id);

}
