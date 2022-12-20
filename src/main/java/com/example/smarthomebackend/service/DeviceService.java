package com.example.smarthomebackend.service;

import com.example.smarthomebackend.model.Device;

public interface DeviceService {
    public Device registerDevice(Device device);
    public Boolean isAlreadySaved(String mac, String model);
}
