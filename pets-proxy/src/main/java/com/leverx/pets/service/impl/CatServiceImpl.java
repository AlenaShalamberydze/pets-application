package com.leverx.pets.service.impl;

import com.leverx.pets.dto.request.CatRequest;
import com.leverx.pets.dto.response.CatResponse;
import com.leverx.pets.repository.CatRepository;
import com.leverx.pets.service.CatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CatServiceImpl implements CatService {

    private final CatRepository catRepository;

    @Override
    @Transactional
    public CatResponse save(CatRequest cat) {
        return catRepository.save(cat);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        catRepository.deleteById(id);
    }


}
