package com.leverx.pets.destinationService.impl;

import com.leverx.pets.destinationService.DestinationService;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.leverx.pets.destinationService.impl.DestinationConstants.*;
import static com.sap.cloud.sdk.cloudplatform.connectivity.HttpClientAccessor.getHttpClient;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;

@Service
public class DestinationServiceImpl implements DestinationService {

    public HttpEntity executeGetAll(String path) throws IOException {
        HttpClient client = getHttpClient(DESTINATION);
        HttpGet get = new HttpGet(URI + path);
        return client.execute(get).getEntity();
    }

    public HttpEntity executePost(String path, StringEntity requestEntity) throws IOException {
        HttpClient client = getHttpClient(DESTINATION);
        HttpPost post = new HttpPost(URI + path);
        post.setEntity(requestEntity);
        post.setHeader(CONTENT_TYPE, HEADER);
        return client.execute(post).getEntity();
    }

    public void executeDelete(String path) throws IOException {
        HttpClient client = getHttpClient(DESTINATION);
        HttpDelete delete = new HttpDelete(URI + path);
        client.execute(delete);
    }

}
