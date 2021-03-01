package com.leverx.pets.repository.util;

import com.leverx.pets.provider.AuthProvider;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Objects.requireNonNull;
import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang3.ObjectUtils.anyNull;

@NoArgsConstructor(access = PRIVATE)
public final class UserPetRepositoryUtil {

    public static HttpEntity<String> getHttpEntityWithoutBody(AuthProvider authProvider) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authProvider.getAuthHeader());
        return new HttpEntity<>(headers);
    }

    public static <T> HttpEntity<T> getHttpEntity(T entity, AuthProvider authProvider) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authProvider.getAuthHeader());
        return new HttpEntity<>(entity, headers);
    }

    public static <T> List<T> formResponseEntityList(ResponseEntity<T[]> entity) {
        return anyNull(entity)
                ? new ArrayList<>(0)
                : asList(requireNonNull(entity.getBody()));
    }

}
