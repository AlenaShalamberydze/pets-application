package com.example.pets.controller;

import com.example.pets.model.User;
import com.example.pets.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/{userId}")
    public ResponseEntity<User> findUserById(@PathVariable long userId) {
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public List<User> findAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(value = "/save")
    public User addUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping(value = "/{userId}")
    public ResponseEntity<User> updateUserData(@PathVariable long userId,
                                               @RequestBody User user
    ) {
        userService.updateUser(user, userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
