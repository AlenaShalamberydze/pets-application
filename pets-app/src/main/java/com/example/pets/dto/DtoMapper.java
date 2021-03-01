package com.example.pets.dto;

import com.example.pets.model.animal.Animal;
import com.example.pets.model.cat.Cat;
import com.example.pets.model.dog.Dog;
import com.example.pets.model.user.User;
import lombok.NoArgsConstructor;

import static org.apache.commons.lang3.ObjectUtils.anyNull;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class DtoMapper {

    public static AnimalDto toDto(Animal animal) {
        return AnimalDto.builder()
                .id(animal.getId())
                .name(animal.getName())
                .age(animal.getAge())
                .userId(animal.getUser().getId())
                .build();
    }

    public static CatDto toCatDto(Cat cat) {
        return CatDto.builder()
                .id(cat.getId())
                .name(cat.getName())
                .age(cat.getAge())
                .character(cat.getCharacter())
                .userId(getUserIdIfExists(cat.getUser()))
                .build();
    }

    public static DogDto toDogDto(Dog dog) {
        return DogDto.builder()
                .id(dog.getId())
                .name(dog.getName())
                .age(dog.getAge())
                .size(dog.getSize())
                .userId(getUserIdIfExists(dog.getUser()))
                .build();
    }

    public static Cat fromCatDto(CatDto catDTO) {
        return Cat.builder()
                .id(catDTO.getId())
                .name(catDTO.getName())
                .age(catDTO.getAge())
                .character(catDTO.getCharacter())
                .build();
    }

    public static Dog fromDogDto(DogDto dogDTO) {
        return Dog.builder()
                .id(dogDTO.getId())
                .name(dogDTO.getName())
                .age(dogDTO.getAge())
                .size(dogDTO.getSize())
                .build();
    }

    private static Long getUserIdIfExists(User user) {
        return anyNull(user) ? 0 : user.getId();
    }

}
