package com.leverx.pets.repository;

import com.leverx.pets.dto.CatDto;

import java.util.List;
import java.util.Optional;

public interface CatRepository {

    List<CatDto> getCats();

    Optional<CatDto> saveCat(CatDto cat);
}
