package com.leverx.pets.service.impl;

import com.leverx.pets.dto.request.UserCatDogRequest;
import com.leverx.pets.dto.response.ResponseEntity;
import com.leverx.pets.dto.response.UserCatDogResponse;
import com.leverx.pets.service.CatService;
import com.leverx.pets.service.DogService;
import com.leverx.pets.service.UserPetServiceTransactionCoordinator;
import com.leverx.pets.service.UserService;
import com.leverx.pets.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static com.leverx.pets.transaction.TransactionalBuilder.buildCatTransactional;
import static com.leverx.pets.transaction.TransactionalBuilder.buildDogTransactional;
import static com.leverx.pets.transaction.TransactionalBuilder.buildUserTransactional;
import static java.util.Arrays.asList;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserPetServiceTransactionCoordinatorImpl implements UserPetServiceTransactionCoordinator {

    private final UserService userService;
    private final CatService catService;
    private final DogService dogService;

    @Override
    public UserCatDogResponse saveUserCatDog(UserCatDogRequest userCatDog) {
        log.info("Transactional Coordinator: saving user+cat+dog into pets-app server DB");

        List<Transactional> transactions = formTransactionalList(userCatDog);
        List<ResponseEntity> entities = new ArrayList<>();

        transactions.forEach(transaction -> {
            try {
                entities.add(transaction.save());
            } catch (HttpClientErrorException | HttpServerErrorException ex) {
                log.error("Troubles saving entities into DB: rollback is started");
                IntStream.range(0, transactions.indexOf(transaction))
                        .mapToObj(transactions::get)
                        .forEach(Transactional::delete);
                throw new HttpClientErrorException(BAD_REQUEST,
                        "failed to save entities into DB");
            }
        });

        return new UserCatDogResponse(entities);
    }

    private List<Transactional> formTransactionalList(UserCatDogRequest userCatDog) {
        return asList(buildUserTransactional(userCatDog.getUser(), userService),
                buildCatTransactional(userCatDog.getCat(), catService),
                buildDogTransactional(userCatDog.getDog(), dogService));
    }

}
