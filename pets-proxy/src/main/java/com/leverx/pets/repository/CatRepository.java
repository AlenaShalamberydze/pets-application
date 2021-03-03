package com.leverx.pets.repository;

import com.leverx.pets.dto.request.CatRequest;
import com.leverx.pets.dto.response.CatResponse;

import java.util.List;

public interface CatRepository {

    List<CatResponse> getAll();

    CatResponse save(CatRequest cat);

    void deleteById(long id);
}
