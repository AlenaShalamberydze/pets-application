package com.leverx.pets.dto;

import lombok.Data;

@Data
public class CatDto {

    private long id;
    private String name;
    private int age;
    private String character;
    private long userId;

}
