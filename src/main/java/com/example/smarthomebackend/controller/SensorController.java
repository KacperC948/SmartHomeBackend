package com.example.smarthomebackend.controller;

import com.example.smarthomebackend.model.Sensor;
import com.example.smarthomebackend.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sensor")
public class SensorController {
    @Autowired
    private SensorService sensorService;

    @GetMapping(value = "/get/{sensorId}")
    public Sensor getSensorById(@PathVariable int sensorId){
        return sensorService.getSensorBySensorId(sensorId);
    }

    @GetMapping(value = "/getAll")
    public List<Sensor> getAllSensors(){
        return sensorService.getAllSensor();
    }
}
