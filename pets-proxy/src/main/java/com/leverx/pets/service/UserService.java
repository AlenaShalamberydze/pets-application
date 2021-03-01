package com.leverx.pets.service;

import com.leverx.pets.model.user.User;

import java.util.Optional;

public interface UserService {

    Optional<User> save(User user);

    void deleteById(long id);


}
