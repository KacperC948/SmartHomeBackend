package com.example.smarthomebackend.controller;

import com.example.smarthomebackend.model.Sensor;
import com.example.smarthomebackend.model.User;
import com.example.smarthomebackend.service.SensorService;
import com.example.smarthomebackend.service.UserService;
import com.example.smarthomebackend.util.SensorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensor")
@CrossOrigin(origins = "http://localhost:3000")
public class SensorController {
    @Autowired
    private SensorService sensorService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/get/{sensorId}")
    public Sensor getSensorById(@PathVariable int sensorId){
        return sensorService.getSensorBySensorId(sensorId);
    }

    @GetMapping(value = "/getAll")
    public List<Sensor> getAllSensors(){
        return sensorService.getAllSensor();
    }

    @GetMapping(value = "/getAll/user/{userId}", produces = "application/json")
    public List<Sensor> getAllSensorsFromUser(@PathVariable int userId){
        User user = userService.getUserById(userId);
        return SensorUtil.getSensorsFromUser(user);
    }
}
