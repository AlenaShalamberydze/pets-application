package com.leverx.pets.repository.impl;

import com.leverx.pets.dto.request.CatRequest;
import com.leverx.pets.dto.response.CatResponse;
import com.leverx.pets.provider.AuthProvider;
import com.leverx.pets.repository.CatRepository;
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
public class CatRepositoryImpl implements CatRepository {

    private static final String CATS = "/cats/";

    @Value(value = "${backend.server.url}")
    private final String backendUrl;
    private final AuthProvider authProvider;

    @Override
    public List<CatResponse> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> httpEntity = getHttpEntityWithoutBody(authProvider.getAuthHeader());
        String url = backendUrl + CATS;
        ResponseEntity<CatResponse[]> cats = restTemplate
                .exchange(url, GET, httpEntity, CatResponse[].class);
        return formResponseEntityList(cats);
    }

    @Override
    public CatResponse save(CatRequest cat) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<CatRequest> httpEntity = getHttpEntity(cat, authProvider.getAuthHeader());
        String url = backendUrl + CATS;
        ResponseEntity<CatResponse> catResponse = restTemplate
                .exchange(url, POST, httpEntity, CatResponse.class);
        return catResponse.getBody();
    }

    @Override
    public void deleteById(long id) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Long> httpEntity = getHttpEntity(id, authProvider.getAuthHeader());
        String url = backendUrl + CATS + id;
        restTemplate
                .exchange(url, DELETE, httpEntity, Void.class);
    }

}
