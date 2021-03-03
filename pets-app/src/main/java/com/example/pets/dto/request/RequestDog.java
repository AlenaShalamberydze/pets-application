package com.example.pets.dto.request;

import com.example.pets.model.dog.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RequestDog {

    private String name;
    private int age;
    private Size size;
    private long userId;
}
