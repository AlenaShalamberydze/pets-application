package com.leverx.pets.model.user;

import com.leverx.pets.dto.response.ResponseEntity;
import lombok.Data;

@Data
public class User implements ResponseEntity {

    private long id;
    private String username;
    private String password;
    private Role role;

}
