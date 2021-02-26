package com.leverx.pets.dto;

import com.leverx.pets.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ResponseDto {

    private List<CatDto> cats;
    private List<DogDto> dogs;
    private List<User> users;

}
