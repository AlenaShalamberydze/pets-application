package com.leverx.pets.dto.request;

import com.leverx.pets.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class UserCatDogRequest {

    private UserRequest user;
    private CatRequest cat;
    private DogRequest dog;

}
