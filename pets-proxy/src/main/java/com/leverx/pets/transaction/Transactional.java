package com.leverx.pets.transaction;

import com.leverx.pets.dto.response.Response;
import com.leverx.pets.dto.response.UserCatDogResponse;

public interface Transactional<T extends Response> {

    T executeSave();

    void rollback();

    void addEntityToResponse(UserCatDogResponse response, T entity);

}
