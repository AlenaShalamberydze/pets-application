package com.example.pets.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static javax.persistence.DiscriminatorType.STRING;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.InheritanceType.SINGLE_TABLE;

@Entity
@Data
@Table(name = "animals")
@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = STRING, name = "TYPE")
public abstract class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "users_id")
    private User user;

}
