package com.leverx.pets.transaction.impl;

import com.leverx.pets.dto.request.UserRequest;
import com.leverx.pets.dto.response.UserResponse;
import com.leverx.pets.service.UserService;
import com.leverx.pets.transaction.Transactional;
import lombok.Builder;
import lombok.Data;

import static com.leverx.pets.transaction.ConstantValues.USER_ID;
import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;
import static org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes;

@Data
@Builder
public class UserTransactional implements Transactional {

    private UserRequest user;
    private final UserService userService;

    @Override
    public UserResponse executeSave() {
        UserResponse userResponse = userService.save(user);
        currentRequestAttributes()
                .setAttribute(USER_ID, userResponse.getId(), SCOPE_REQUEST);
        return userResponse;
    }

    @Override
    public void rollback() {
        long id = (long) currentRequestAttributes().getAttribute(USER_ID, SCOPE_REQUEST);
        userService.deleteById(id);
    }

}
