package com.leverx.pets.repository.util;

import com.sap.cloud.sdk.cloudplatform.connectivity.HttpDestination;
import lombok.NoArgsConstructor;
import org.apache.http.client.HttpClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static com.sap.cloud.sdk.cloudplatform.connectivity.DestinationAccessor.getDestination;
import static com.sap.cloud.sdk.cloudplatform.connectivity.HttpClientAccessor.getHttpClient;
import static java.util.Arrays.asList;
import static java.util.Objects.requireNonNull;
import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang3.ObjectUtils.allNull;

@NoArgsConstructor(access = PRIVATE)
public final class UserPetRepositoryUtil {

    public static HttpClient getHttpClientWithDestination(){
        HttpDestination destination = getDestination("cf-proxy-pets").asHttp();
        return getHttpClient(destination);
    }

    public static HttpEntity<String> getHttpEntityWithoutBody(String authProvider) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authProvider);
        return new HttpEntity<>(headers);
    }

    public static <T> HttpEntity<T> getHttpEntity(T entity, String authProvider) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authProvider);
        return new HttpEntity<>(entity, headers);
    }

    public static <T> List<T> formResponseEntityList(ResponseEntity<T[]> entity) {
        return allNull(entity)
                ? new ArrayList<>(0)
                : asList(requireNonNull(entity.getBody()));
    }

}
