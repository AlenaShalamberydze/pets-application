package com.leverx.pets.repository.implDestination;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leverx.pets.RepositoryException;
import com.leverx.pets.dto.request.DogRequest;
import com.leverx.pets.dto.response.DogResponse;
import com.leverx.pets.repository.DogRepository;
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
public class DogRepositoryDestinationImpl implements DogRepository {

    private static final String DOGS = "/dogs/";
    private static final String HEADER = "application/json";

    private final ObjectMapper objectMapper;

    @Value(value = "${backend.server.url}")
    private final String backendUrl;

    @Override
    public List<DogResponse> getAll() {
        HttpClient client = getHttpClientWithDestination();
        HttpGet get = new HttpGet(backendUrl + DOGS);
        DogResponse[] dogs;
        try {
            HttpEntity httpEntity = client.execute(get).getEntity();
            dogs = objectMapper
                    .readValue(EntityUtils.toString(httpEntity),
                            DogResponse[].class);
        } catch (IOException e) {
            log.error("Troubles getting dogs from DB");
            throw new RepositoryException("Troubles getting dogs from DB");
        }

        return asList(dogs);
    }

    @Override
    public DogResponse save(DogRequest dog) {
        HttpClient client = getHttpClientWithDestination();
        HttpPost post = new HttpPost(backendUrl + DOGS);
        DogResponse dogResponse;
        try {
            post.setEntity(new StringEntity(objectMapper.writeValueAsString(dog)));
            post.setHeader("Accept", HEADER);
            post.setHeader("Content-type", HEADER);
            HttpEntity httpEntity = client.execute(post).getEntity();
            dogResponse = objectMapper
                    .readValue(EntityUtils.toString(httpEntity),
                            DogResponse.class);
        } catch (IOException e) {
            log.error("Troubles saving dog into DB");
            throw new RepositoryException("Troubles saving dog into DB");
        }

        return dogResponse;
    }

    @Override
    public void deleteById(long id) {
        HttpClient client = getHttpClientWithDestination();
        HttpDelete delete = new HttpDelete(backendUrl + DOGS + id);
        try {
            client.execute(delete);
        } catch (IOException e) {
            log.error("Troubles deleting dog from DB");
            throw new RepositoryException("Troubles deleting dog from DB");
        }
    }

}
