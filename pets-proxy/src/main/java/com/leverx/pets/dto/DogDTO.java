package com.leverx.pets.dto;

import com.leverx.pets.model.dog.Size;
import lombok.Data;

@Data
public class DogDto {

    private long id;
    private String name;
    private int age;
    private Size size;
    private long userId;

}
