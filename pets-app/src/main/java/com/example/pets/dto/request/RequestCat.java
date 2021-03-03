package com.example.pets.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RequestCat {

    private String name;
    private int age;
    private String character;
    private long userId;



}
