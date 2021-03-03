package com.leverx.pets.transaction.impl;

import com.leverx.pets.dto.request.CatRequest;
import com.leverx.pets.dto.response.CatResponse;
import com.leverx.pets.service.CatService;
import com.leverx.pets.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class CatTransactional implements Transactional {

    private final CatRequest cat;
    private final CatService catService;
    private CatResponse catResponse;

    @Override
    public CatResponse save() {
        catResponse = catService.save(cat);
        return catResponse;
    }

    @Override
    public void delete() {
        catService.deleteById(catResponse.getId());
    }

}
