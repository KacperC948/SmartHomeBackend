package com.example.smarthomebackend.service;

import com.example.smarthomebackend.model.User;

public interface UserService {
    User addUser(User user);
    User getUserByMac(String mac);
    User getUserById(int id);
}
