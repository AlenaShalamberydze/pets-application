package com.example.pets.dto.response;

import com.example.pets.model.dog.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ResponseDog {

    private long id;
    private String name;
    private int age;
    private Size size;
    private long userId;
}
