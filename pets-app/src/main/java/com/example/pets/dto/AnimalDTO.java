package com.example.pets.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AnimalDTO {

    private final long id;
    private final String name;
    private final double age;
    private final long userId;
}
