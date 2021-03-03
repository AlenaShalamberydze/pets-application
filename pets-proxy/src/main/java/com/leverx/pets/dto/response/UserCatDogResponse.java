package com.leverx.pets.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserCatDogResponse {

    private List<ResponseEntity> entities;

}
