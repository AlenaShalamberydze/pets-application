package com.leverx.pets.service.impl;

import com.leverx.pets.dto.request.UserCatDogRequest;
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

import java.util.LinkedList;
import java.util.List;

import static com.leverx.pets.transaction.TransactionalBuilder.buildCatTransactional;
import static com.leverx.pets.transaction.TransactionalBuilder.buildDogTransactional;
import static com.leverx.pets.transaction.TransactionalBuilder.buildUserTransactional;
import static java.util.Arrays.asList;

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
        List<Transactional> executedTransactions = new LinkedList<>();
        UserCatDogResponse response = new UserCatDogResponse();

        transactions.forEach(transaction -> {
            try {
                transaction.addEntityToResponse(response, transaction.executeSave());
                executedTransactions.add(transaction);
            } catch (HttpClientErrorException | HttpServerErrorException ex) {
                log.error("Troubles saving entities into DB: rollback has started");
                executedTransactions.forEach(Transactional::rollback);
                throw new HttpClientErrorException(ex.getStatusCode(),
                        "failed to save entities into DB");
            }
        });

        return response;
    }

    private List<Transactional> formTransactionalList(UserCatDogRequest userCatDog) {
        return asList(buildUserTransactional(userCatDog.getUser(), userService),
                buildCatTransactional(userCatDog.getCat(), catService),
                buildDogTransactional(userCatDog.getDog(), dogService));
    }

}
