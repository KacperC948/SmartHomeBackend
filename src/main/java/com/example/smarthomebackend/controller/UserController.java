package com.example.smarthomebackend.controller;

import com.example.smarthomebackend.model.User;
import com.example.smarthomebackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping(value = "/get/{id}", consumes = "application/json", produces = "application/json")
    public User getUserById(@PathVariable int id){
        return userService.getUserById(id);
    }
}
