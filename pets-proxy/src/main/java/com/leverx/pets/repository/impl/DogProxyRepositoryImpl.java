package com.leverx.pets.repository.impl;

import com.leverx.pets.dto.DogDTO;
import com.leverx.pets.provider.AuthProvider;
import com.leverx.pets.repository.DogProxyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;
import static com.leverx.pets.repository.util.PetRepositoryUtil.getHttpEntity;

@Component
@RequiredArgsConstructor
public class DogProxyRepositoryImpl implements DogProxyRepository {

    @Value(value = "${backend.server.url}")
    private String backendUrl;
    private static final String DOGS = "/dogs";
    private final AuthProvider authProvider;

    @Override
    public List<DogDTO> getDogs() {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> httpEntity = getHttpEntity(authProvider);
        List body = restTemplate.exchange(backendUrl + DOGS,
                GET,
                httpEntity,
                List.class)
                .getBody();
        return body;
    }

}
