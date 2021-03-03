package com.leverx.pets.service;

import com.leverx.pets.dto.request.CatRequest;
import com.leverx.pets.dto.response.CatResponse;

public interface CatService {

    CatResponse save(CatRequest cat);

    void deleteById(long id);

}
