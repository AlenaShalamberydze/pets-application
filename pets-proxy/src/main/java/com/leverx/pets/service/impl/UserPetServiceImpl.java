package com.leverx.pets.service.impl;

import com.leverx.pets.dto.response.AllEntitiesResponse;
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

    private final UserRepository userRepository;
    private final CatRepository catRepository;
    private final DogRepository dogRepository;

    @Override
    public AllEntitiesResponse getAll() {
        log.info("Getting all cats, dogs and users from pets-app service");
        return new AllEntitiesResponse(userRepository.getAll(),
                catRepository.getAll(),
                dogRepository.getAll());
    }

}
