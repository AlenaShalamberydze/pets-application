package com.leverx.pets.repository.impl;

import com.leverx.pets.model.user.User;
import com.leverx.pets.provider.AuthProvider;
import com.leverx.pets.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.leverx.pets.repository.util.PetRepositoryUtil.getHttpEntity;
import static java.util.Arrays.asList;
import static java.util.Optional.ofNullable;
import static org.springframework.http.HttpMethod.GET;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private static final String USERS = "/users";

    @Value(value = "${backend.server.url}")
    private final String backendUrl;
    private final AuthProvider authProvider;

    @Override
    public List<User> getUsers() {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> httpEntity = getHttpEntity(authProvider);

        ResponseEntity<User[]> users = restTemplate.exchange(backendUrl + USERS,
                GET,
                httpEntity,
                User[].class);

        return asList(
                ofNullable(users.getBody())
                        .orElseGet(users::getBody));
    }

}
