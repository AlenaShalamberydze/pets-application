package com.leverx.pets.repository;

import com.leverx.pets.dto.DogDTO;

import java.util.List;

public interface DogProxyRepository {

    List<DogDTO> getDogs();
}
