package com.sharma.libmanagmentsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sharma.libmanagmentsystem.services.*;
import com.sharma.libmanagmentsystem.model.*;
import com.sharma.libmanagmentsystem.requests.UserCreateRequest;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    // add user to our database
    @PostMapping("/user")
    public void addUser(@RequestBody UserCreateRequest userCreateRequest) {
        userService.addUser(userCreateRequest.to());
    }

    // get all users
    @GetMapping("/user/all")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    // get a user
    @GetMapping("/user/{userId}")
    public User getBookById(@PathVariable int userId) {
        return userService.getUserById(userId);
    }

}
