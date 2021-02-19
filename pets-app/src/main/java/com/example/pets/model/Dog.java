package com.example.pets.model;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@DiscriminatorValue("DOG")
public class Dog extends Animal {

    private String size;

}