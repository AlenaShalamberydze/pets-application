package com.leverx.pets.dto.request;

import com.leverx.pets.model.user.Role;
import lombok.Data;

@Data
public class UserRequest {

    private String username;
    private String password;
    private Role role;

}
