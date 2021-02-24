package com.example.pets.repository;

import com.example.pets.model.cat.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatRepository extends JpaRepository<Cat, Long> {

    List<Cat> getAllByUserId(long id);
}
