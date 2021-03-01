package com.leverx.pets.service;

import com.leverx.pets.model.dto.CatDto;

import java.util.Optional;

public interface CatService {

    Optional<CatDto> save(CatDto cat);

    void deleteById(long id);

}
