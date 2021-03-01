package com.leverx.pets.service.impl;

import com.leverx.pets.dto.AllEntitiesDto;
import com.leverx.pets.dto.CatDto;
import com.leverx.pets.dto.DogDto;
import com.leverx.pets.model.user.User;
import com.leverx.pets.repository.CatRepository;
import com.leverx.pets.repository.DogRepository;
import com.leverx.pets.repository.UserRepository;
import com.leverx.pets.service.PetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PetServiceImpl implements PetService {

    private final CatRepository catProxyRepository;
    private final DogRepository dogProxyRepository;
    private final UserRepository userProxyRepository;

    @Override
    public AllEntitiesDto getUsersCatsDogs() {
        log.info("Getting all users-cats-dogs from pets-app service");
        return new AllEntitiesDto(catProxyRepository.getCats(),
                dogProxyRepository.getDogs(),
                userProxyRepository.getUsers());
    }

    @Override
    @Transactional
    public Optional<User> saveUser(User user) {
        return userProxyRepository.saveUser(user);
    }

    @Override
    @Transactional
    public Optional<CatDto> saveCat(CatDto cat) {
        return catProxyRepository.saveCat(cat);
    }

    @Override
    @Transactional
    public Optional<DogDto> saveDog(DogDto dog) {
        return dogProxyRepository.saveDog(dog);
    }

}
