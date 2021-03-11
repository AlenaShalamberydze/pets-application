package com.leverx.pets.repository.implDestination;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leverx.pets.RepositoryException;
import com.leverx.pets.dto.request.UserRequest;
import com.leverx.pets.dto.response.UserResponse;
import com.leverx.pets.model.user.User;
import com.leverx.pets.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

import static com.leverx.pets.repository.util.UserPetRepositoryUtil.getHttpClientWithDestination;
import static java.util.Arrays.asList;


@Repository
@RequiredArgsConstructor
@Slf4j
@Primary
public class UserRepositoryDestinationImpl implements UserRepository {

    private static final String USERS = "/users/";
    private static final String HEADER = "application/json";

    private final ObjectMapper objectMapper;

    @Value(value = "${backend.server.url}")
    private final String backendUrl;

    @Override
    public List<User> getAll() {
        HttpClient client = getHttpClientWithDestination();
        HttpGet get = new HttpGet(backendUrl + USERS);
        User[] users;
        try {
            HttpEntity httpEntity = client.execute(get).getEntity();
            users = objectMapper
                    .readValue(EntityUtils.toString(httpEntity),
                            User[].class);
        } catch (IOException e) {
            log.error("Troubles getting users from DB");
            throw new RepositoryException("Troubles getting users from DB");
        }

        return asList(users);
    }

    @Override
    public UserResponse save(UserRequest user) {
        HttpClient client = getHttpClientWithDestination();
        HttpPost post = new HttpPost(backendUrl + USERS);
        UserResponse userResponse;
        try {
            post.setEntity(new StringEntity(objectMapper.writeValueAsString(user)));
            post.setHeader("Accept", HEADER);
            post.setHeader("Content-type", HEADER);
            HttpEntity httpEntity = client.execute(post).getEntity();
            userResponse = objectMapper
                    .readValue(EntityUtils.toString(httpEntity),
                            UserResponse.class);
        } catch (IOException e) {
            log.error("Troubles saving user into DB");
            throw new RepositoryException("Troubles saving user into DB");
        }

        return userResponse;
    }

    @Override
    public void deleteById(long id) {
        HttpClient client = getHttpClientWithDestination();
        HttpDelete delete = new HttpDelete(backendUrl + USERS + id);
        try {
            client.execute(delete);
        } catch (IOException e) {
            log.error("Troubles deleting user from DB");
            throw new RepositoryException("Troubles deleting user from DB");
        }
    }

}
