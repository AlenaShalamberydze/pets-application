package com.leverx.pets.repository.impl;

import com.leverx.pets.dto.CatDto;
import com.leverx.pets.provider.AuthProvider;
import com.leverx.pets.repository.CatRepository;
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
public class CatRepositoryImpl implements CatRepository {

    private static final String CATS = "/cats";

    @Value(value = "${backend.server.url}")
    private final String backendUrl;
    private final AuthProvider authProvider;

    @Override
    public List<CatDto> getCats() {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> httpEntity = getHttpEntity(authProvider);

        ResponseEntity<CatDto[]> cats = restTemplate.exchange(backendUrl + CATS,
                GET,
                httpEntity,
                CatDto[].class);

        return asList(
                ofNullable(cats.getBody())
                        .orElseGet(cats::getBody));
    }

}
