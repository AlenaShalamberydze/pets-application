package com.leverx.pets.repository.util;

import com.leverx.pets.provider.AuthProvider;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class PetRepositoryUtil {

    public static HttpEntity<String> getHttpEntityWithoutBody(AuthProvider authProvider){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authProvider.getAuthHeader());
        return new HttpEntity<>(headers);
    }

    public static <T> HttpEntity<T> getHttpEntity(T entity, AuthProvider authProvider){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authProvider.getAuthHeader());
        return new HttpEntity<>(entity, headers);
    }

}
