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
        if (deviceRepository.findByMacAndModel(mac, model) != null){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public int getMaxId() {
        String max = "";
        try{
            max = deviceRepository.getMaxId();
            if(max.equalsIgnoreCase("NULL")){
                return -1;
            } else {
                return Integer.parseInt(max);
            }
        } catch(Exception e) {
            return -1;
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

}
