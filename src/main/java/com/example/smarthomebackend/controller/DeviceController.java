package com.example.smarthomebackend.controller;

import com.example.smarthomebackend.model.Device;
import com.example.smarthomebackend.model.Sensor;
import com.example.smarthomebackend.service.DeviceService;
import com.example.smarthomebackend.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @Autowired
    private SensorService sensorService;

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public Device registerDevice(@RequestBody Device device) {
        Device deviceTemp;
        if (deviceService.getMaxId() == -1){
            deviceTemp = deviceService.saveDevice(device);
            device.setSensors(sensorService.saveSensors(device.getSensors(), device));
        } else {
            if (deviceService.isAlreadySaved(device.getMac(), device.getModel())) {
                deviceTemp = deviceService.findByMacAndModel(device.getMac(), device.getModel());
                List<Sensor> sensorList = sensorService.findSensorsByDevice(deviceTemp);
                deviceTemp.setSensors(sensorList);
            } else {
                deviceTemp = deviceService.saveDevice(device);
                deviceTemp.setSensors(sensorService.saveSensors(device.getSensors(), device));
            }
        }
        return deviceTemp;
    }
}
