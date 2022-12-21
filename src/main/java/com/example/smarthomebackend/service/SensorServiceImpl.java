package com.example.smarthomebackend.service;

import com.example.smarthomebackend.model.Device;
import com.example.smarthomebackend.model.Sensor;
import com.example.smarthomebackend.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorServiceImpl implements SensorService{

    @Autowired
    SensorRepository sensorRepository;

    @Override
    public int getMaxId() {
        String max = "";
        try{
            max = sensorRepository.getMaxId();
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
    public List<Sensor> saveSensors(List<Sensor> sensors, Device deviceId) {
        for (Sensor sensor : sensors) {
            sensor.setDevice(deviceId);
        }
        return sensorRepository.saveAll(sensors);
    }

    @Override
    public List<Sensor> findSensorsByDevice(Device deviceId) {
        return sensorRepository.findSensorsByDevice(deviceId);
    }
}
