package com.leverx.pets.transaction.impl;

import com.leverx.pets.dto.request.DogRequest;
import com.leverx.pets.dto.response.DogResponse;
import com.leverx.pets.dto.response.UserCatDogResponse;
import com.leverx.pets.service.DogService;
import com.leverx.pets.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import static com.leverx.pets.transaction.ConstantValues.DOG_ID;
import static com.leverx.pets.transaction.ConstantValues.USER_ID;
import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;
import static org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes;

@Data
@AllArgsConstructor
@Builder
public class DogTransactional implements Transactional {

    private final DogRequest dogRequest;
    private final DogService dogService;

    @Override
    public DogResponse executeSave() {
        long userId = (long) currentRequestAttributes().getAttribute(USER_ID, SCOPE_REQUEST);
        dogRequest.setUserId(userId);
        DogResponse dogResponse = dogService.save(dogRequest);
        currentRequestAttributes()
                .setAttribute(DOG_ID, dogResponse.getId(), SCOPE_REQUEST);
        return dogResponse;
    }

    @Override
    public void rollback() {
        long id = (long) currentRequestAttributes().getAttribute(DOG_ID, SCOPE_REQUEST);
        dogService.deleteById(id);
    }

    @Override
    public <T> void addEntityToResponse(UserCatDogResponse response, T entity) {
        response.setDog((DogResponse) entity);
    }

}
