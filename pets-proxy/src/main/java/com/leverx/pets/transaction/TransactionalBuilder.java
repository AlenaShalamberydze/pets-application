package com.leverx.pets.transaction;

import com.leverx.pets.dto.request.CatRequest;
import com.leverx.pets.dto.request.DogRequest;
import com.leverx.pets.dto.request.UserRequest;
import com.leverx.pets.service.CatService;
import com.leverx.pets.service.DogService;
import com.leverx.pets.service.UserService;
import com.leverx.pets.transaction.impl.CatTransactional;
import com.leverx.pets.transaction.impl.DogTransactional;
import com.leverx.pets.transaction.impl.UserTransactional;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class TransactionalBuilder {

    public static UserTransactional buildUserTransactional(UserRequest user, UserService service) {
        return UserTransactional
                .builder()
                .userService(service)
                .user(user)
                .build();
    }

    public static CatTransactional buildCatTransactional(CatRequest cat, CatService service) {
        return CatTransactional
                .builder()
                .catService(service)
                .catRequest(cat)
                .build();
    }

    public static DogTransactional buildDogTransactional(DogRequest dog, DogService service) {
        return DogTransactional
                .builder()
                .dogService(service)
                .dogRequest(dog)
                .build();
    }

}
