package com.leverx.pets.model.cat;

import com.leverx.pets.model.animal.Animal;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cat extends Animal {

    private String character;

}
