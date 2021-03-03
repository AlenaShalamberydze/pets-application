package com.example.pets.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ResponseAnimal {

    private long id;
    private String name;
    private int age;
    private long userId;

}
