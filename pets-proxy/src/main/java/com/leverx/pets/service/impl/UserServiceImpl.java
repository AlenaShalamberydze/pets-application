package com.leverx.pets.service.impl;

import com.leverx.pets.dto.request.UserRequest;
import com.leverx.pets.dto.response.UserResponse;
import com.leverx.pets.repository.UserRepository;
import com.leverx.pets.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserResponse save(UserRequest user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

}
