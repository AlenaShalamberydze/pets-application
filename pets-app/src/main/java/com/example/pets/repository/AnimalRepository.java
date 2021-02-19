package com.example.pets.repository;

import com.example.pets.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    Long findUserIdById(long id);

    List<Animal> findAnimalsByUserId(long id);
}
