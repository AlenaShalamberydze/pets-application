package com.example.pets.service;

import com.example.pets.dto.request.RequestCat;
import com.example.pets.dto.response.ResponseCat;
import com.example.pets.model.cat.Cat;

import java.util.List;

public interface CatService {

    Cat getById(long id);

    List<ResponseCat> getAll();

    List<ResponseCat> getAllByUserId(long userId);

    ResponseCat save(RequestCat requestCat);

    ResponseCat update(RequestCat requestCat, long id);

    void deleteById(long id);

}
