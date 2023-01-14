package com.example.smarthomebackend.service;

import com.example.smarthomebackend.model.Device;

import java.util.List;

public interface DeviceService {
    public Device registerDevice(Device device);
    public Boolean isAlreadySaved(String mac, String model);
    public Device saveDevice(Device device);
    public Device findByMacAndModel(String mac, String model);
    Device getDeviceById(int deviceId);
    List<Device> getAllSensors();
}
