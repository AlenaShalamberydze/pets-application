package com.example.pets.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class CatDTO {

    private long id;
    private final String name;
    private final int age;
    private final String character;
    private final long userId;

}
