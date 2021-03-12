package com.leverx.pets.repository.implDestination;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leverx.pets.exception.RepositoryException;
import com.leverx.pets.destinationService.DestinationService;
import com.leverx.pets.dto.request.CatRequest;
import com.leverx.pets.dto.response.CatResponse;
import com.leverx.pets.repository.CatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

import static com.leverx.pets.repository.implDestination.RepositoryConstants.CATS;
import static com.leverx.pets.repository.implDestination.RepositoryConstants.CHARSET;
import static java.util.Arrays.asList;

@Repository
@RequiredArgsConstructor
@Slf4j
@Primary
public class CatRepositoryDestinationImpl implements CatRepository {

    private final DestinationService destinationService;
    private final ObjectMapper objectMapper;

    @Override
    public List<CatResponse> getAll() {
        CatResponse[] cats;
        try {
            HttpEntity httpEntity = destinationService.executeGetAll(CATS);
            cats = objectMapper
                    .readValue(EntityUtils.toString(httpEntity, CHARSET),
                            CatResponse[].class);
        } catch (IOException e) {
            log.error("Troubles getting cats from DB");
            throw new RepositoryException("Troubles getting cats from DB");
        }

        return asList(cats);
    }

    @Override
    public CatResponse save(CatRequest cat) {
        CatResponse catResponse;
        try {
            StringEntity requestCat = new StringEntity(objectMapper.writeValueAsString(cat));
            HttpEntity httpEntity = destinationService.executePost(CATS, requestCat);
            catResponse = objectMapper
                    .readValue(EntityUtils.toString(httpEntity, CHARSET),
                            CatResponse.class);
        } catch (IOException e) {
            log.error("Troubles saving cat into DB");
            throw new RepositoryException("Troubles saving cat into DB");
        }

        return catResponse;
    }

    @Override
    public void deleteById(long id) {
        try {
            destinationService.executeDelete(CATS + id);
        } catch (IOException e) {
            log.error("Troubles deleting cat from DB");
            throw new RepositoryException("Troubles deleting cat from DB");
        }
    }

}
