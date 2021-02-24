package com.example.pets.service;

import com.example.pets.model.user.User;

import java.util.List;

public interface UserService {

    User getById(long id);

    List<User> getAll();

    User save(User user);

    User update(User user);

    void deleteById(long id);

}