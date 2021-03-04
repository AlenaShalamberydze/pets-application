package com.leverx.pets.repository.impl;

import com.leverx.pets.dto.request.UserRequest;
import com.leverx.pets.dto.response.UserResponse;
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

import static com.leverx.pets.repository.util.UserPetRepositoryUtil.formResponseEntityList;
import static com.leverx.pets.repository.util.UserPetRepositoryUtil.getHttpEntity;
import static com.leverx.pets.repository.util.UserPetRepositoryUtil.getHttpEntityWithoutBody;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;


@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private static final String USERS = "/users";
    private static final String DELETE_USER = "/users/";

    @Value(value = "${backend.server.url}")
    private final String backendUrl;
    private final AuthProvider authProvider;

    @Override
    public List<User> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> httpEntity = getHttpEntityWithoutBody(authProvider.getAuthHeader());
        String url = backendUrl + USERS;
        ResponseEntity<User[]> users = restTemplate
                .exchange(url, GET, httpEntity, User[].class);
        return formResponseEntityList(users);
    }

    @Override
    public UserResponse save(UserRequest user) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<UserRequest> httpEntity = getHttpEntity(user, authProvider.getAuthHeader());
        String url = backendUrl + USERS;
        ResponseEntity<UserResponse> userResponse = restTemplate
                .exchange(url, POST, httpEntity, UserResponse.class);
        return userResponse.getBody();
    }

    @Override
    public void deleteById(long id) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Long> httpEntity = getHttpEntity(id, authProvider.getAuthHeader());
        String url = backendUrl + DELETE_USER + id;
        restTemplate
                .exchange(url, DELETE, httpEntity, Void.class);
    }

}
