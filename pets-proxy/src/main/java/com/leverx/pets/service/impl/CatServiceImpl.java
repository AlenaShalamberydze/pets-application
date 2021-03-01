package com.leverx.pets.service.impl;

import com.leverx.pets.model.dto.CatDto;
import com.leverx.pets.repository.CatRepository;
import com.leverx.pets.service.CatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CatServiceImpl implements CatService {

    private final CatRepository catRepository;

    @Override
    @Transactional
    public Optional<CatDto> save(CatDto cat) {
        return catRepository.save(cat);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        catRepository.deleteById(id);
    }


}
