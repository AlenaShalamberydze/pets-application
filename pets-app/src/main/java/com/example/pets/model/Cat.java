package com.example.pets.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@DiscriminatorValue("CAT")
public class Cat extends Animal{

    @Column(name = "character")
    private String character;

}
