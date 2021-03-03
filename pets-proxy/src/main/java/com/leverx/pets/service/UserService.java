package com.leverx.pets.service;

import com.leverx.pets.model.user.User;

public interface UserService {

    User save(User user);

    void deleteById(long id);


}
