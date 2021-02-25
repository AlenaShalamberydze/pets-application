package com.leverx.pets.repository.util;

import com.leverx.pets.provider.AuthProvider;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class PetRepositoryUtil {

    public static HttpEntity<String> getHttpEntity(AuthProvider authProvider){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authProvider.getAuthHeader());
        return new HttpEntity<>(headers);
    }

}
