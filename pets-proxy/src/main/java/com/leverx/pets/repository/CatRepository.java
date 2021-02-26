package com.leverx.pets.repository;

import com.leverx.pets.dto.CatDto;

import java.util.List;

public interface CatRepository {

    List<CatDto> getCats();
}
