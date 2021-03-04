package com.leverx.pets.dto.response;

import com.leverx.pets.model.dog.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DogResponse implements ResponseEntity {

    private final String type = "dog";
    private long id;
    private String name;
    private int age;
    private Size size;
    private long userId;

}
