package com.example.pets.service;

import com.example.pets.dto.CatDto;
import com.example.pets.model.cat.Cat;

import java.util.List;

public interface CatService {

    Cat getById(long id);

    List<CatDto> getAll();

    List<CatDto> getAllByUserId(long userId);

    CatDto save(CatDto cat);

    CatDto update(CatDto catDTO);

    void deleteById(long id);

}
