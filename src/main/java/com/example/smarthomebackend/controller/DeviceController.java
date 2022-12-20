package com.example.smarthomebackend.controller;

import com.example.smarthomebackend.model.Device;
import com.example.smarthomebackend.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public Device registerDevice(@RequestBody Device device) {

        return device;
    }
}
