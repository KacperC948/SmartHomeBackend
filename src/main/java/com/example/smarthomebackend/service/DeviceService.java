package com.example.smarthomebackend.service;

import com.example.smarthomebackend.model.Device;

import java.util.List;

public interface DeviceService {
    Device registerDevice(Device device);
    Boolean isAlreadySaved(String mac, String model);
    Device saveDevice(Device device);
    Device findByMacAndModel(String mac, String model);
    Device getDeviceById(int deviceId);
    List<Device> getAllSensors();
}
