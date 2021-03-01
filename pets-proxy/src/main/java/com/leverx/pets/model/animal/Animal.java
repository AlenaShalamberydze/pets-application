package com.leverx.pets.model.animal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Animal {

    private long id;
    private String name;
    private int age;
    private long userId;

}
