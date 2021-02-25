package com.example.pets.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AnimalDTO {

    private long id;
    private String name;
    private int age;
    private long userId;

}
