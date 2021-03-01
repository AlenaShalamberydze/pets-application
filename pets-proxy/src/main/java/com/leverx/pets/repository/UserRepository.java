package com.leverx.pets.repository;

import com.leverx.pets.model.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> getUsers();

    Optional<User> saveUser(User user);
}
