package com.leverx.pets.model.dog;

import com.leverx.pets.model.animal.Animal;
import lombok.Data;

@Data
public class Dog extends Animal {

    private Size size;

}