package com.example.pets.dto;

import com.example.pets.model.Animal;
import com.example.pets.model.Cat;
import com.example.pets.model.Dog;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class DTOMapper {

    public static AnimalDTO toDto(Animal animal) {
        return AnimalDTO.builder()
                .id(animal.getId())
                .name(animal.getName())
                .age(animal.getAge())
                .build();
    }

    public static CatDTO toCatDto(Cat cat) {
        return CatDTO.builder()
                .id(cat.getId())
                .name(cat.getName())
                .age(cat.getAge())
                .character(cat.getCharacter())
                .userId(cat.getUser().getId())
                .build();
    }

    public static DogDTO toDogDto(Dog dog) {
        return DogDTO.builder()
                .id(dog.getId())
                .name(dog.getName())
                .age(dog.getAge())
                .size(dog.getSize())
                .userId(dog.getUser().getId())
                .build();
    }

}
