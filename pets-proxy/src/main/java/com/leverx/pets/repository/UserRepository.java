package com.leverx.pets.repository;

import com.leverx.pets.dto.request.UserRequest;
import com.leverx.pets.dto.response.UserResponse;
import com.leverx.pets.model.user.User;

import java.util.List;

public interface UserRepository {

    List<User> getAll();

    UserResponse save(UserRequest user);

    void deleteById(long id);
}
