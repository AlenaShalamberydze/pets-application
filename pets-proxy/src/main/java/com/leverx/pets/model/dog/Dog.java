package com.leverx.pets.model.dog;

import com.leverx.pets.model.animal.Animal;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Dog extends Animal {

    private Size size;

}