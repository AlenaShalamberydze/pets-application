package com.leverx.pets.transaction.impl;

import com.leverx.pets.model.user.User;
import com.leverx.pets.service.UserService;
import com.leverx.pets.transaction.Transactional;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserTransactional implements Transactional {

    private User user;
    private final UserService userService;

    @Override
    public User save() {
        user = userService.save(user);
        return user;
    }

    @Override
    public void delete() {
        userService.deleteById(user.getId());
    }

}
