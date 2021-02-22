package com.example.pets.service.impl;

import com.example.pets.dto.DTOMapper;
import com.example.pets.dto.DogDTO;
import com.example.pets.exception.NotFoundException;
import com.example.pets.model.Dog;
import com.example.pets.repository.DogRepository;
import com.example.pets.repository.UserRepository;
import com.example.pets.service.DogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.example.pets.dto.DTOMapper.fromDogDto;
import static com.example.pets.dto.DTOMapper.toDogDto;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class DogServiceImpl implements DogService {

    private final DogRepository dogRepository;
    private final UserRepository userRepository;

    @Override
    public Dog getDogById(long id) {
        return dogRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Dog not found"));
    }

    @Override
    public List<DogDTO> getDogsByUserId(long id) {
        return dogRepository.getAllByUserId(id).stream()
                .map(DTOMapper::toDogDto)
                .collect(toList());
    }

    @Override
    @Transactional
    public DogDTO saveDog(DogDTO dogDTO) {
        Dog dog = fromDogDto(dogDTO);
        dog.setUser(userRepository.findById(dogDTO.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found")));
        return toDogDto(dogRepository.save(dog));
    }

    @Override
    @Transactional
    public DogDTO updateDog(DogDTO dogDTO) {
        Dog dog = fromDogDto(dogDTO);
        dog.setUser(userRepository.findById(dogDTO.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found")));
        return toDogDto(dogRepository.save(dog));
    }

    @Override
    public void deleteDog(long id) {
        dogRepository.deleteById(id);
    }
}
