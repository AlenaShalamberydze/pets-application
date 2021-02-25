package com.leverx.pets.repository;

import com.leverx.pets.dto.CatDTO;

import java.util.List;

public interface CatProxyRepository {

    List<CatDTO> getCats();
}
