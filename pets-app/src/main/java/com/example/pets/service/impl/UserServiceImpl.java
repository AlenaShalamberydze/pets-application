package com.example.pets.service.impl;

import com.example.pets.model.User;
import com.example.pets.repository.AnimalRepository;
import com.example.pets.repository.UserRepository;
import com.example.pets.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(long userId) {
        userRepository.delete(getUserById(userId));
    }

    @Override
    @Transactional
    public User updateUser(User user, long userId) {
        user.setId(userId);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User getUserById(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("The owner with such id not found"));
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
