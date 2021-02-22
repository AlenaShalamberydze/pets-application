package com.example.pets.repository;

import com.example.pets.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

    List<Animal> getAllByUserId(long id);
}
