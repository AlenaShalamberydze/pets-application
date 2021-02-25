package com.leverx.pets.service.impl;

import com.leverx.pets.repository.CatProxyRepository;
import com.leverx.pets.repository.DogProxyRepository;
import com.leverx.pets.repository.UserProxyRepository;
import com.leverx.pets.service.PetProxyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.util.Arrays.asList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PetProxyServiceImpl implements PetProxyService {

    private final CatProxyRepository catProxyRepository;
    private final DogProxyRepository dogProxyRepository;
    private final UserProxyRepository userProxyRepository;

    @Override
    public List<Object> getUsersCatsDogs() {
        return asList(catProxyRepository.getCats(),
                dogProxyRepository.getDogs(),
                userProxyRepository.getUsers());
    }
}
