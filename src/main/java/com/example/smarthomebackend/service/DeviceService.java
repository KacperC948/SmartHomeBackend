package com.example.smarthomebackend.service;

import com.example.smarthomebackend.model.Device;

public interface DeviceService {
    public Device registerDevice(Device device);
    public Boolean isAlreadySaved(String mac, String model);
    public int getMaxId();
    public Device saveDevice(Device device);
    public Device findByMacAndModel(String mac, String model);
}
