package com.example.smarthomebackend.service;

import com.example.smarthomebackend.model.Device;
import com.example.smarthomebackend.model.User;

import java.util.List;

public interface UserService {
    User addUser(User user);
    User getUserByMac(String mac);
    void updateUser(List<Device> devices, int userId);
}
