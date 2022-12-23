package com.example.smarthomebackend.service;

import com.example.smarthomebackend.model.Device;
import com.example.smarthomebackend.model.User;
import com.example.smarthomebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserByMac(String mac) {
        return userRepository.findUserByMac(mac);
    }

    @Override
    public void updateUser(List<Device> devices, int userId) {
        userRepository.updateUser(devices, userId);
    }
}
