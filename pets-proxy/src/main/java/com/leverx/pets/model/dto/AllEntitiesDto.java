package com.leverx.pets.model.dto;

import com.leverx.pets.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AllEntitiesDto {

    private List<CatDto> cats;
    private List<DogDto> dogs;
    private List<User> users;

}
