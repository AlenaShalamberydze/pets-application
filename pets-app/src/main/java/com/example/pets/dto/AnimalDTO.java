package com.example.pets.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class AnimalDTO {

    private final long id;
    private final String name;
    private final int age;
    private final long userId;

}
