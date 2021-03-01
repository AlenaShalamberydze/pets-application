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
import java.util.Optional;

import static com.leverx.pets.repository.util.UserPetRepositoryUtil.getHttpEntity;
import static com.leverx.pets.repository.util.UserPetRepositoryUtil.getHttpEntityWithoutBody;
import static com.leverx.pets.repository.util.UserPetRepositoryUtil.formResponseEntityList;
import static java.util.Optional.ofNullable;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private static final String USERS = "/users";

    @Value(value = "${backend.server.url}")
    private final String backendUrl;
    private final AuthProvider authProvider;

    @Override
    public List<User> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> httpEntity = getHttpEntityWithoutBody(authProvider);

        ResponseEntity<User[]> users = restTemplate.exchange(
                backendUrl + USERS,
                GET,
                httpEntity,
                User[].class);

        return formResponseEntityList(users);
    }

    @Override
    public Optional<User> save(User user) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<User> httpEntity = getHttpEntity(user, authProvider);

        ResponseEntity<User> userResponseEntity = restTemplate.exchange(
                backendUrl + USERS,
                POST,
                httpEntity,
                User.class);

        return ofNullable(userResponseEntity.getBody());
    }

    @Override
    public void deleteById(long id) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Long> httpEntity = getHttpEntity(id, authProvider);

        restTemplate.exchange(
                backendUrl + USERS,
                DELETE,
                httpEntity,
                Void.class);
    }

}
