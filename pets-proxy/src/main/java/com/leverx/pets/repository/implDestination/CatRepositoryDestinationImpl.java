package com.leverx.pets.repository.implDestination;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.leverx.pets.dto.request.CatRequest;
import com.leverx.pets.dto.response.CatResponse;
import com.leverx.pets.repository.CatRepository;
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
public class CatRepositoryDestinationImpl implements CatRepository {

    private static final String CATS = "/cats/";
    private static final String HEADER = "application/json";

    @Value(value = "${backend.server.url}")
    private final String backendUrl;

    @Override
    public List<CatResponse> getAll() {
        HttpClient client = getHttpClientWithDestination();
        HttpGet get = new HttpGet(backendUrl + CATS);
        CatResponse[] cats;
        try {
            HttpEntity httpEntity = client.execute(get).getEntity();
            cats = new Gson()
                    .fromJson(EntityUtils.toString(httpEntity),
                            CatResponse[].class);
        } catch (IOException e) {
            log.error("Troubles getting cats from DB");
            throw new RuntimeException("Troubles getting cats from DB");
        }

        return asList(cats);
    }

    @Override
    public CatResponse save(CatRequest cat) {
        HttpClient client = getHttpClientWithDestination();
        HttpPost post = new HttpPost(backendUrl + CATS);
        CatResponse catResponse;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            post.setEntity(new StringEntity(objectMapper.writeValueAsString(cat)));
            post.setHeader("Accept", HEADER);
            post.setHeader("Content-type", HEADER);
            HttpEntity httpEntity = client.execute(post).getEntity();
            catResponse = new Gson()
                    .fromJson(EntityUtils.toString(httpEntity),
                            CatResponse.class);
        } catch (IOException e) {
            log.error("Troubles saving cat into DB");
            throw new RuntimeException("Troubles saving cat into DB");
        }

        return catResponse;
    }

    @Override
    public void deleteById(long id) {
        HttpClient client = getHttpClientWithDestination();
        HttpDelete delete = new HttpDelete(backendUrl + CATS + id);
        try {
            client.execute(delete);
        } catch (IOException e) {
            log.error("Troubles deleting cat from DB");
            throw new RuntimeException("Troubles deleting cat from DB");
        }
    }

}
