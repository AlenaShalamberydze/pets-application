package com.example.pets.service;

import com.example.pets.model.User;

import java.util.List;

public interface UserService {

    User getUserById(long userId);

    List<User> getAllUsers();

    User saveUser(User user);

    User updateUser(User user);

    void deleteUser(long userId);

}