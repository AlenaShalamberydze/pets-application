package com.leverx.pets.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CatRequest {

    private String name;
    private int age;
    private String character;
    private long userId;
}
