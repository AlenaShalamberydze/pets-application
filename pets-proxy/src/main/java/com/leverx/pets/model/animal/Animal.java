package com.leverx.pets.model.animal;

import com.leverx.pets.model.user.User;
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

    private User user;

}
