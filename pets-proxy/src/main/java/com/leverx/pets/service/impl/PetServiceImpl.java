package com.leverx.pets.service.impl;

import com.leverx.pets.dto.ResponseDto;
import com.leverx.pets.repository.CatRepository;
import com.leverx.pets.repository.DogRepository;
import com.leverx.pets.repository.UserRepository;
import com.leverx.pets.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    private final CatRepository catProxyRepository;
    private final DogRepository dogProxyRepository;
    private final UserRepository userProxyRepository;

    @Override
    public ResponseDto getUsersCatsDogs() {
        return new ResponseDto(catProxyRepository.getCats(),
                dogProxyRepository.getDogs(),
                userProxyRepository.getUsers());
    }
}
