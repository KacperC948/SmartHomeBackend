package com.example.smarthomebackend.service;

import com.example.smarthomebackend.model.Device;
import com.example.smarthomebackend.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public Device registerDevice(Device device) {
        return deviceRepository.save(device);
    }

    @Override
    public Boolean isAlreadySaved(String mac, String model) {
        return null;
    }
}
