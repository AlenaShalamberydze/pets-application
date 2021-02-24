package com.example.pets.repository;

import com.example.pets.model.dog.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DogRepository extends JpaRepository<Dog, Long> {

    List<Dog> getAllByUserId(long id);
}
