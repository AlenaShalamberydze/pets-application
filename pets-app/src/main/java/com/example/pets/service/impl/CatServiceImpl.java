package com.example.pets.service.impl;

import com.example.pets.dto.CatDTO;
import com.example.pets.dto.DTOMapper;
import com.example.pets.exception.NotFoundException;
import com.example.pets.model.Cat;
import com.example.pets.repository.CatRepository;
import com.example.pets.repository.UserRepository;
import com.example.pets.service.CatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.example.pets.dto.DTOMapper.fromCatDto;
import static com.example.pets.dto.DTOMapper.toCatDto;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class CatServiceImpl implements CatService {

    private final CatRepository catRepository;
    private final UserRepository userRepository;


    @Override
    public Cat getCatById(long id) {
        return catRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cat not found"));
    }

    @Override
    public List<CatDTO> getCatsByUserId(long id) {
        return catRepository.getAllByUserId(id).stream()
                .map(DTOMapper::toCatDto)
                .collect(toList());
    }

    @Override
    @Transactional
    public CatDTO saveCat(CatDTO catDTO) {
        Cat cat = fromCatDto(catDTO);
        cat.setUser(userRepository.findById(catDTO.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found")));
        return toCatDto(catRepository.save(cat));
    }

    @Override
    @Transactional
    public CatDTO updateCat(CatDTO catDTO) {
        Cat cat = fromCatDto(catDTO);
        cat.setUser(userRepository.findById(catDTO.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found")));
        return toCatDto(catRepository.save(cat));
    }

    @Override
    public void deleteCat(long id) {
        catRepository.deleteById(id);
    }

}
