package com.leverx.pets.repository;

import com.leverx.pets.dto.DogDto;

import java.util.List;

public interface DogRepository {

    List<DogDto> getDogs();
}
