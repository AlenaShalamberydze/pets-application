package com.example.pets.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CatDto {

    private long id;
    private String name;
    private int age;
    private String character;
    private long userId;

}
