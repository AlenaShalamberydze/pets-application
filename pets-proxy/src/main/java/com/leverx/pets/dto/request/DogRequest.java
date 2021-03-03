package com.leverx.pets.dto.request;

import com.leverx.pets.model.dog.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DogRequest {

    private String name;
    private int age;
    private Size size;
    private long userId;

}