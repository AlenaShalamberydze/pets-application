package com.leverx.pets.repository;

import com.leverx.pets.model.user.User;

import java.util.List;

public interface UserProxyRepository {

    List<User> getUsers();
}
