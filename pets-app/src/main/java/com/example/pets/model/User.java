package com.example.pets.model;

//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//import java.util.List;
//
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "users")
//public class User {
//
//    public User(String name, String city, int age) {
//        this.name = name;
//        this.city = city;
//        this.age = age;
//    }
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @Column(name = "name")
//    private String name;
//
//    @Column(name = "city")
//    private String city;
//
//    @Column(name = "age")
//    private int age;
//
//    @OneToMany(mappedBy = "user",
//            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
//                    CascadeType.DETACH, CascadeType.REFRESH})
//    private List<Cat> cats;
//
//    @OneToMany(mappedBy = "user",
//            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
//                    CascadeType.DETACH, CascadeType.REFRESH})
//    private List<Dog> dogs;
//
//}




import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "user")
    private List<Animal> animals;

}
