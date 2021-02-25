package com.example.pets.service.impl;

import com.example.pets.exception.NotFoundException;
import com.example.pets.exception.UserAlreadyExistsException;
import com.example.pets.model.user.User;
import com.example.pets.repository.UserRepository;
import com.example.pets.service.AnimalService;
import com.example.pets.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AnimalService animalService;

    @Override
    public User getById(long id) {
        log.info("Getting user from DB by id: {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Troubles getting user from DB: user not found");
                    return new NotFoundException("User not found");
                });
    }

    @Override
    public List<User> getAll() {
        log.info("Getting all users from DB");
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User save(User user) {
        log.info("Saving user into DB");
        Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());
        if (optionalUser.isPresent()) {
            log.error("Troubles! User already exists");
            throw new UserAlreadyExistsException("User already exists!");
        }
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        log.info("Updating user in DB");
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        log.info("Deleting user from DB by id: {}", id);
        animalService.getAllByUserId(id)
                .forEach(animal -> {
                    animal.setUser(null);
                    animalService.save(animal);
                });
        userRepository.delete(getById(id));
    }

}
