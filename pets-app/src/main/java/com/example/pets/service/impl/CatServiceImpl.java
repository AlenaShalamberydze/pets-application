package com.example.pets.service.impl;

import com.example.pets.dto.CatDto;
import com.example.pets.dto.DtoMapper;
import com.example.pets.exception.NotFoundException;
import com.example.pets.model.cat.Cat;
import com.example.pets.repository.CatRepository;
import com.example.pets.repository.UserRepository;
import com.example.pets.service.CatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.example.pets.dto.DtoMapper.fromCatDto;
import static com.example.pets.dto.DtoMapper.toCatDto;
import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@RequiredArgsConstructor
public class CatServiceImpl implements CatService {

    private final CatRepository catRepository;
    private final UserRepository userRepository;

    @Override
    public Cat getById(long id) {
        log.info("Getting cat from DB by id: {}", id);
        return catRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Troubles getting Cat from DB: cat not found");
                    return new NotFoundException("Cat not found");
                });
    }

    @Override
    public List<CatDto> getAll() {
        log.info("Getting all cats from DB");
        return catRepository.findAll().stream()
                .map(DtoMapper::toCatDto)
                .collect(toList());
    }

    @Override
    public List<CatDto> getAllByUserId(long id) {
        log.info("Getting cats from DB by userId: {}", id);
        return catRepository.getAllByUserId(id).stream()
                .map(DtoMapper::toCatDto)
                .collect(toList());
    }

    @Override
    @Transactional
    public CatDto save(CatDto catDTO) {
        log.info("Saving cat into DB");
        Cat cat = fromCatDto(catDTO);
        cat.setUser(userRepository.findById(catDTO.getUserId())
                .orElseThrow(() -> {
                    log.error("Troubles saving Cat into DB: user not found");
                    return new NotFoundException("User not found");
                }));
        return toCatDto(catRepository.save(cat));
    }

    @Override
    @Transactional
    public CatDto update(CatDto catDTO) {
        log.info("Updating cat in DB");
        Cat cat = fromCatDto(catDTO);
        cat.setUser(userRepository.findById(catDTO.getUserId())
                .orElseThrow(() -> {
                    log.error("Troubles updating Cat: user not found");
                    return new NotFoundException("User not found");
                }));
        return toCatDto(catRepository.save(cat));
    }

    @Override
    public void deleteById(long id) {
        log.info("Deleting cat from DB by id: {}", id);
        catRepository.deleteById(id);
    }

}
