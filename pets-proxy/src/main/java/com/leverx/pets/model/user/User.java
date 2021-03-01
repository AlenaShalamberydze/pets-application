package com.leverx.pets.model.user;

import lombok.Data;

@Data
public class User {

    private long id;
    private String username;
    private String password;
    private Role role;

}
