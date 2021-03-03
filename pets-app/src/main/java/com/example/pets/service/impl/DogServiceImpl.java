package com.example.pets.service.impl;

import com.example.pets.dto.DtoMapper;
import com.example.pets.dto.request.RequestDog;
import com.example.pets.dto.response.ResponseDog;
import com.example.pets.exception.NotFoundException;
import com.example.pets.model.dog.Dog;
import com.example.pets.repository.DogRepository;
import com.example.pets.repository.UserRepository;
import com.example.pets.service.DogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.example.pets.dto.DtoMapper.fromDogDto;
import static com.example.pets.dto.DtoMapper.toDogDto;
import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@RequiredArgsConstructor
public class DogServiceImpl implements DogService {

    private final DogRepository dogRepository;
    private final UserRepository userRepository;

    @Override
    public Dog getById(long id) {
        log.info("Getting dog from DB by id: {}", id);
        return dogRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Troubles getting Dog from DB: dog not found");
                    return new NotFoundException("Dog not found");
                });
    }

    @Override
    public List<ResponseDog> getAllByUserId(long id) {
        log.info("Getting dogs from DB by userId: {}", id);
        return dogRepository.getAllByUserId(id).stream()
                .map(DtoMapper::toDogDto)
                .collect(toList());
    }

    @Override
    public List<ResponseDog> getAll() {
        log.info("Getting all dogs from DB");
        return dogRepository.findAll().stream()
                .map(DtoMapper::toDogDto)
                .collect(toList());
    }

    @Override
    @Transactional
    public ResponseDog save(RequestDog requestDog) {
        log.info("Saving dog into DB");
        Dog dog = fromDogDto(requestDog);
        dog.setUser(userRepository.findById(requestDog.getUserId())
                .orElseThrow(() -> {
                    log.error("Troubles saving Dog into DB: user not found");
                    return new NotFoundException("User not found");
                }));
        return toDogDto(dogRepository.save(dog));
    }

    @Override
    @Transactional
    public ResponseDog update(RequestDog requestDog, long id) {
        log.info("Updating dog in DB");
        Dog dog = fromDogDto(requestDog);
        dog.setId(id);
        dog.setUser(userRepository.findById(requestDog.getUserId())
                .orElseThrow(() -> {
                    log.error("Troubles updating Dog: user not found");
                    return new NotFoundException("User not found");
                }));
        return toDogDto(dogRepository.save(dog));
    }

    @Override
    public void deleteById(long id) {
        log.info("Deleting dog from DB by id: {}", id);
        dogRepository.deleteById(id);
    }
}
