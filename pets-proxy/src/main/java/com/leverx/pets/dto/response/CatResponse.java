package com.leverx.pets.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatResponse implements ResponseEntity {

    private final String type = "cat";
    private long id;
    private String name;
    private int age;
    private String character;
    private long userId;

}
