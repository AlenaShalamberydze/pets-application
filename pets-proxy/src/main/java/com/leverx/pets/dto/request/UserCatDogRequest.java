package com.leverx.pets.dto.request;

import com.leverx.pets.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCatDogRequest {

    private User user;
    private CatRequest cat;
    private DogRequest dog;

}
