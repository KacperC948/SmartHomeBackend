package com.example.smarthomebackend.controller;

import com.example.smarthomebackend.model.Device;
import com.example.smarthomebackend.service.DeviceService;
import com.example.smarthomebackend.service.SensorService;
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

    @Autowired
    private SensorService sensorService;

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public Device registerDevice(@RequestBody Device device) {
        Device deviceTemp;
        if (deviceService.getMaxId() == -1){
//            device.setId(1);
            device.setSensors(sensorService.saveSensors(device.getSensors(), device));
            deviceTemp = deviceService.saveDevice(device);
//            deviceTemp.setSensors(sensorService.saveSensors(device.getSensors()));
        }
        else {
            if (deviceService.isAlreadySaved(device.getMac(), device.getModel())) {
                deviceTemp = deviceService.findByMacAndModel(device.getMac(), device.getModel());
                deviceTemp.setSensors(sensorService.findSensorsByDeviceId(deviceTemp.getId()));
            }
            else {
                device.setId(deviceService.getMaxId() + 1);
                deviceTemp = deviceService.saveDevice(device);
                deviceTemp.setSensors(sensorService.saveSensors(device.getSensors(), device));
            }
        }
        return deviceTemp;
    }
}
