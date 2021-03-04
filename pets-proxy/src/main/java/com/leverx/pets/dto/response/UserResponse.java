package com.leverx.pets.dto.response;

import com.leverx.pets.model.user.Role;
import lombok.Data;

@Data
public class UserResponse {

    private long id;
    private String username;
    private String password;
    private Role role;

}
