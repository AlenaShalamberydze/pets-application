package com.leverx.pets.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCatDogRequest {

    private UserRequest user;
    private CatRequest cat;
    private DogRequest dog;

}
