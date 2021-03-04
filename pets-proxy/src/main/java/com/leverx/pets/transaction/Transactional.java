package com.leverx.pets.transaction;

import com.leverx.pets.dto.response.UserCatDogResponse;

public interface Transactional {

    <T> T executeSave();

    void rollback();

    <T> void addEntityToResponse(UserCatDogResponse response, T entity);

}
