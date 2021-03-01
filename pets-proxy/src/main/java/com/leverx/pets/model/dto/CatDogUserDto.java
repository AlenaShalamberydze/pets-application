package com.leverx.pets.model.dto;

import com.leverx.pets.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CatDogUserDto {

    private CatDto cat;
    private DogDto dog;
    private User user;

}
