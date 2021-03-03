package com.example.pets.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ResponseCat {

    private long id;
    private String name;
    private int age;
    private String character;
    private long userId;



}
