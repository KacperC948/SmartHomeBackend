package com.example.smarthomebackend.controller;

import com.example.smarthomebackend.model.Device;
import com.example.smarthomebackend.model.Sensor;
import com.example.smarthomebackend.model.User;
import com.example.smarthomebackend.service.DeviceService;
import com.example.smarthomebackend.service.SensorService;
import com.example.smarthomebackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @Autowired
    private SensorService sensorService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public Device registerDevice(@RequestBody Device device) throws ParseException {
        Device deviceTemp = new Device();
        if (deviceService.isAlreadySaved(device.getMac(), device.getModel())) {
            deviceTemp = deviceService.findByMacAndModel(device.getMac(), device.getModel());
            List<Sensor> sensorList = sensorService.findSensorsByDevice(deviceTemp);
            deviceTemp.setSensors(sensorList);
            return deviceTemp;
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            User user = userService.getUserByMac(device.getMac());
            device.setCreationDate(formatter.parse(formatter.format(date)));
            device.setUser(user);
            deviceService.saveDevice(device);
            device.setSensors(sensorService.saveSensors(device.getSensors(), device));
            user.getDevices().add(device);
            return device;
        }
    }

    @GetMapping(value = "/{deviceId}/getSensors")
    public List<Sensor> getAllSensorsFromDevice(@PathVariable int deviceId) {
        Device device = deviceService.getAllSensorsFromDevice(deviceId);
        return device.getSensors();
    }

    @GetMapping(value = "getAll")
    public List<Device> getAllSensors() {
        return deviceService.getAllSensors();
    }
}