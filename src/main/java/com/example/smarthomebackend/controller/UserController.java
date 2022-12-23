package com.example.smarthomebackend.controller;

import com.example.smarthomebackend.model.User;
import com.example.smarthomebackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }
}
