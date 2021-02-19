package com.example.pets.service.impl;

import com.example.pets.dto.DTOMapper;
import com.example.pets.dto.DogDTO;
import com.example.pets.model.Dog;
import com.example.pets.repository.DogRepository;
import com.example.pets.repository.UserRepository;
import com.example.pets.service.DogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.example.pets.dto.DTOMapper.toDogDto;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class DogServiceImpl implements DogService {

    private final DogRepository dogRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public DogDTO saveDog(Dog dog, long userIdId) {
        dog.setUser(userRepository.findById(userIdId)
                .orElseThrow(() -> new RuntimeException("User not found")));
        return toDogDto(dogRepository.save(dog));
    }

    @Override
    @Transactional
    public DogDTO updateDog(Dog dog, long userId, long id) {
        dog.setUser(userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found")));
        dog.setId(id);
        return toDogDto(dogRepository.save(dog));
    }


    @Override
    @Transactional
    public List<DogDTO> getDogsByUserId(long id) {
        return dogRepository.getAllByUserId(id).stream()
                .map(DTOMapper::toDogDto)
                .collect(toList());
    }

    @Override
    @Transactional
    public Dog getDogById(long id) {
        return dogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal not found"));
    }

    @Override
    @Transactional
    public void deleteDog(long id) {
        dogRepository.deleteById(id);
    }
}
