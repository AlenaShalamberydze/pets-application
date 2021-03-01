package com.leverx.pets.service.impl;

import com.leverx.pets.model.dto.AllEntitiesDto;
import com.leverx.pets.repository.CatRepository;
import com.leverx.pets.repository.DogRepository;
import com.leverx.pets.repository.UserRepository;
import com.leverx.pets.service.UserPetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserPetServiceImpl implements UserPetService {

    private final CatRepository catRepository;
    private final DogRepository dogRepository;
    private final UserRepository userRepository;

    @Override
    public AllEntitiesDto getAll() {
        log.info("Getting all cats-dogs-users from pets-app service");
        return new AllEntitiesDto(catRepository.getAll(),
                dogRepository.getAll(),
                userRepository.getAll());
    }

}
