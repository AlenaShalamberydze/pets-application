package com.leverx.pets.repository.implRestTemplate;

import com.leverx.pets.dto.request.DogRequest;
import com.leverx.pets.dto.response.DogResponse;
import com.leverx.pets.provider.AuthProvider;
import com.leverx.pets.repository.DogRepository;
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
public class DogRepositoryImpl implements DogRepository {

    private static final String DOGS = "/dogs/";

    @Value(value = "${backend.server.url}")
    private final String backendUrl;
    private final AuthProvider authProvider;

    @Override
    public List<DogResponse> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> httpEntity = getHttpEntityWithoutBody(authProvider.getAuthHeader());
        String url = backendUrl + DOGS;
        ResponseEntity<DogResponse[]> dogs = restTemplate
                .exchange(url, GET, httpEntity, DogResponse[].class);
        return formResponseEntityList(dogs);
    }

    @Override
    public DogResponse save(DogRequest dog) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<DogRequest> httpEntity = getHttpEntity(dog, authProvider.getAuthHeader());
        String url = backendUrl + DOGS;
        ResponseEntity<DogResponse> dogResponse = restTemplate
                .exchange(url, POST, httpEntity, DogResponse.class);
        return dogResponse.getBody();
    }

    @Override
    public void deleteById(long id) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Long> httpEntity = getHttpEntity(id, authProvider.getAuthHeader());
        String url = backendUrl + DOGS + id;
        restTemplate
                .exchange(url, DELETE, httpEntity, Void.class);
    }

}
