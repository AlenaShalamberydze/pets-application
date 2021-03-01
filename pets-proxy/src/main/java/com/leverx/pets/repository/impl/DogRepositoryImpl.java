package com.leverx.pets.repository.impl;

import com.leverx.pets.dto.DogDto;
import com.leverx.pets.provider.AuthProvider;
import com.leverx.pets.repository.DogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static com.leverx.pets.repository.util.PetRepositoryUtil.getHttpEntity;
import static com.leverx.pets.repository.util.PetRepositoryUtil.getHttpEntityWithoutBody;
import static java.util.Arrays.asList;
import static java.util.Optional.ofNullable;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Repository
@RequiredArgsConstructor
public class DogRepositoryImpl implements DogRepository {

    private static final String DOGS = "/dogs";

    @Value(value = "${backend.server.url}")
    private final String backendUrl;
    private final AuthProvider authProvider;

    @Override
    public List<DogDto> getDogs() {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> httpEntity = getHttpEntityWithoutBody(authProvider);

        ResponseEntity<DogDto[]> dogs = restTemplate.exchange(
                backendUrl + DOGS,
                GET,
                httpEntity,
                DogDto[].class);

        return asList(
                ofNullable(dogs.getBody())
                        .orElseGet(dogs::getBody));
    }

    @Override
    public Optional<DogDto> saveDog(DogDto dog) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<DogDto> httpEntity = getHttpEntity(dog, authProvider);

        ResponseEntity<DogDto> dogDto = restTemplate.exchange(
                backendUrl + DOGS,
                POST,
                httpEntity,
                DogDto.class);

        return ofNullable(dogDto.getBody());
    }

}
