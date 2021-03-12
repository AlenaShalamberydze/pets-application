package com.leverx.pets.repository.implDestination;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leverx.pets.exception.RepositoryException;
import com.leverx.pets.destinationService.DestinationService;
import com.leverx.pets.dto.request.UserRequest;
import com.leverx.pets.dto.response.UserResponse;
import com.leverx.pets.model.user.User;
import com.leverx.pets.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

import static com.leverx.pets.repository.implDestination.RepositoryConstants.CHARSET;
import static com.leverx.pets.repository.implDestination.RepositoryConstants.USERS;
import static java.util.Arrays.asList;


@Repository
@RequiredArgsConstructor
@Slf4j
@Primary
public class UserRepositoryDestinationImpl implements UserRepository {

    private final DestinationService destinationService;
    private final ObjectMapper objectMapper;

    @Override
    public List<User> getAll() {
        User[] users;
        try {
            HttpEntity httpEntity = destinationService.executeGetAll(USERS);
            users = objectMapper
                    .readValue(EntityUtils.toString(httpEntity, CHARSET),
                            User[].class);
        } catch (IOException e) {
            log.error("Troubles getting users from DB");
            throw new RepositoryException("Troubles getting users from DB");
        }

        return asList(users);
    }

    @Override
    public UserResponse save(UserRequest user) {
        UserResponse userResponse;
        try {
            StringEntity requestUser = new StringEntity(objectMapper.writeValueAsString(user));
            HttpEntity httpEntity = destinationService.executePost(USERS, requestUser);
            userResponse = objectMapper
                    .readValue(EntityUtils.toString(httpEntity, CHARSET),
                            UserResponse.class);
        } catch (IOException e) {
            log.error("Troubles saving user into DB");
            throw new RepositoryException("Troubles saving user into DB");
        }

        return userResponse;
    }

    @Override
    public void deleteById(long id) {
        try {
            destinationService.executeDelete(USERS + id);
        } catch (IOException e) {
            log.error("Troubles deleting user from DB");
            throw new RepositoryException("Troubles deleting user from DB");
        }
    }

}
