package com.example.pets.service.impl;

import com.example.pets.dto.CatDTO;
import com.example.pets.dto.DTOMapper;
import com.example.pets.model.Cat;
import com.example.pets.repository.CatRepository;
import com.example.pets.repository.UserRepository;
import com.example.pets.service.CatService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.example.pets.dto.DTOMapper.toCatDto;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class CatServiceImpl implements CatService {

    private final CatRepository catRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public CatDTO saveCat(Cat cat, long userId) {
        cat.setUser(userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found")));
        return toCatDto(catRepository.save(cat));
    }

    @Override
    @Transactional
    public CatDTO updateCat(Cat cat, long userId, long id) {
        cat.setUser(userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found")));
        cat.setId(id);

        return toCatDto(catRepository.save(cat));
    }

    @Override
    @Transactional
    public void deleteCat(long id) {
        catRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<CatDTO> getCatsByUserId(long id) {
        return catRepository.getAllByUserId(id).stream()
                .map(DTOMapper::toCatDto)
                .collect(toList());
    }

    @Override
    @Transactional
    public Cat getCatById(long id) {
        return catRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet not found"));
    }

}
