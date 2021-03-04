package com.leverx.pets.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCatDogResponse {

    private UserResponse user;
    private CatResponse cat;
    private DogResponse dog;

}
