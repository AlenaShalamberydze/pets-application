package com.leverx.pets.repository.impl;

import com.leverx.pets.model.dto.CatDto;
import com.leverx.pets.provider.AuthProvider;
import com.leverx.pets.repository.CatRepository;
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
public class CatRepositoryImpl implements CatRepository {

    private static final String CATS = "/cats";

    @Value(value = "${backend.server.url}")
    private final String backendUrl;
    private final AuthProvider authProvider;

    @Override
    public List<CatDto> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> httpEntity = getHttpEntityWithoutBody(authProvider);

        ResponseEntity<CatDto[]> cats = restTemplate.exchange(
                backendUrl + CATS,
                GET,
                httpEntity,
                CatDto[].class);

        return formResponseEntityList(cats);
    }

    @Override
    public Optional<CatDto> save(CatDto cat) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<CatDto> httpEntity = getHttpEntity(cat, authProvider);

        ResponseEntity<CatDto> catDto = restTemplate.exchange(
                backendUrl + CATS,
                POST,
                httpEntity,
                CatDto.class);

        return ofNullable(catDto.getBody());
    }

    @Override
    public void deleteById(long id) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Long> httpEntity = getHttpEntity(id, authProvider);

        restTemplate.exchange(
                backendUrl + CATS,
                DELETE,
                httpEntity,
                Void.class);
    }

}
