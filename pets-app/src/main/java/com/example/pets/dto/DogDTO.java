package com.example.pets.dto;

import com.example.pets.model.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class DogDTO {

    private long id;
    private final String name;
    private final int age;
    private final Size size;
    private final long userId;
}
