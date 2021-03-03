package com.leverx.pets.dto.response;

import com.leverx.pets.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AllEntitiesResponse {

    private List<User> users;
    private List<CatResponse> cats;
    private List<DogResponse> dogs;

}
