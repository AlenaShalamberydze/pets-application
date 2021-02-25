package com.leverx.pets.model.user;

import com.leverx.pets.model.animal.Animal;
import lombok.Data;

import java.util.List;

@Data
public class User {

    private long id;

    private String username;

    private String password;

    private Role role;

    private List<Animal> animals;

}
