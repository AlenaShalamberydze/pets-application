package com.example.pets.dto;

import com.example.pets.dto.request.RequestCat;
import com.example.pets.dto.request.RequestDog;
import com.example.pets.dto.response.ResponseAnimal;
import com.example.pets.dto.response.ResponseCat;
import com.example.pets.dto.response.ResponseDog;
import com.example.pets.model.animal.Animal;
import com.example.pets.model.cat.Cat;
import com.example.pets.model.dog.Dog;
import com.example.pets.model.user.User;
import lombok.NoArgsConstructor;
import sun.misc.Request;

import static org.apache.commons.lang3.ObjectUtils.anyNull;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class DtoMapper {

    public static ResponseAnimal toDto(Animal animal) {
        return ResponseAnimal.builder()
                .id(animal.getId())
                .name(animal.getName())
                .age(animal.getAge())
                .userId(animal.getUser().getId())
                .build();
    }

    public static ResponseCat toCatDto(Cat cat) {
        return ResponseCat.builder()
                .id(cat.getId())
                .name(cat.getName())
                .age(cat.getAge())
                .character(cat.getCharacter())
                .userId(getUserIdIfExists(cat.getUser()))
                .build();
    }

    public static ResponseDog toDogDto(Dog dog) {
        return ResponseDog.builder()
                .id(dog.getId())
                .name(dog.getName())
                .age(dog.getAge())
                .size(dog.getSize())
                .userId(getUserIdIfExists(dog.getUser()))
                .build();
    }

    public static Cat fromCatDto(RequestCat requestCat) {
        return Cat.builder()
                .name(requestCat.getName())
                .age(requestCat.getAge())
                .character(requestCat.getCharacter())
                .build();
    }

    public static Dog fromDogDto(RequestDog requestDog) {
        return Dog.builder()
                .name(requestDog.getName())
                .age(requestDog.getAge())
                .size(requestDog.getSize())
                .build();
    }

    private static Long getUserIdIfExists(User user) {
        return anyNull(user) ? 0 : user.getId();
    }

}
