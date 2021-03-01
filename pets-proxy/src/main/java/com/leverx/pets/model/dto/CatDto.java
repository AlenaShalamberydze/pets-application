package com.leverx.pets.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatDto {

    private long id;
    private String name;
    private int age;
    private String character;
    private long userId;

}
