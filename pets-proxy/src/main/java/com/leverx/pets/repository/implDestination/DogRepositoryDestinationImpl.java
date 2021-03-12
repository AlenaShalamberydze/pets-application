package com.leverx.pets.repository.implDestination;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leverx.pets.exception.RepositoryException;
import com.leverx.pets.destinationService.DestinationService;
import com.leverx.pets.dto.request.DogRequest;
import com.leverx.pets.dto.response.DogResponse;
import com.leverx.pets.repository.DogRepository;
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
import static com.leverx.pets.repository.implDestination.RepositoryConstants.DOGS;
import static java.util.Arrays.asList;

@Repository
@RequiredArgsConstructor
@Slf4j
@Primary
public class DogRepositoryDestinationImpl implements DogRepository {

    private final DestinationService destinationService;
    private final ObjectMapper objectMapper;

    @Override
    public List<DogResponse> getAll() {
        DogResponse[] dogs;
        try {
            HttpEntity httpEntity = destinationService.executeGetAll(DOGS);
            dogs = objectMapper
                    .readValue(EntityUtils.toString(httpEntity, CHARSET),
                            DogResponse[].class);
        } catch (IOException e) {
            log.error("Troubles getting dogs from DB");
            throw new RepositoryException("Troubles getting dogs from DB");
        }

        return asList(dogs);
    }

    @Override
    public DogResponse save(DogRequest dog) {
        DogResponse dogResponse;
        try {
            StringEntity requestDog = new StringEntity(objectMapper.writeValueAsString(dog));
            HttpEntity httpEntity = destinationService.executePost(DOGS, requestDog);
            dogResponse = objectMapper
                    .readValue(EntityUtils.toString(httpEntity, CHARSET),
                            DogResponse.class);
        } catch (IOException e) {
            log.error("Troubles saving dog into DB");
            throw new RepositoryException("Troubles saving dog into DB");
        }

        return dogResponse;
    }

    @Override
    public void deleteById(long id) {
        try {
            destinationService.executeDelete(DOGS + id);
        } catch (IOException e) {
            log.error("Troubles deleting dog from DB");
            throw new RepositoryException("Troubles deleting dog from DB");
        }
    }

}
