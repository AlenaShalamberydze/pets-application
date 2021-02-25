package com.leverx.pets.model.animal;

import com.leverx.pets.model.user.User;
import lombok.Data;

@Data
public abstract class Animal {

    private long id;

    private String name;

    private int age;

    private User user;

}
