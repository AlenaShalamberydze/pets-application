package com.leverx.pets.repository;

import com.leverx.pets.model.dto.CatDto;

import java.util.List;
import java.util.Optional;

public interface CatRepository {

    List<CatDto> getAll();

    Optional<CatDto> save(CatDto cat);

    void deleteById(long id);
}
