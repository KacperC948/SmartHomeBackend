package com.example.smarthomebackend.service;

import com.example.smarthomebackend.model.Device;
import com.example.smarthomebackend.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if (deviceRepository.findByMacAndModel(mac, model) != null){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public Device saveDevice(Device device) {
        return deviceRepository.save(device);
    }

    @Override
    public Device findByMacAndModel(String mac, String model) {
        return deviceRepository.findByMacAndModel(mac, model);
    }

    @Override
    public Device getDeviceById(int deviceId) {
        return deviceRepository.getDeviceById(deviceId);
    }

    @Override
    public List<Device> getAllSensors() {
        return deviceRepository.findAll();
    }

}
