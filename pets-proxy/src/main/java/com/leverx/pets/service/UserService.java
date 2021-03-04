package com.leverx.pets.service;

import com.leverx.pets.dto.request.UserRequest;
import com.leverx.pets.dto.response.UserResponse;

public interface UserService {

    UserResponse save(UserRequest user);

    void deleteById(long id);


}
