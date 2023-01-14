package com.example.smarthomebackend.service;

import com.example.smarthomebackend.model.Device;
import com.example.smarthomebackend.model.Sensor;

import java.util.List;

public interface SensorService {
    //public int getMaxId();
    public List<Sensor> saveSensors(List<Sensor> sensors, Device deviceId);
    public List<Sensor> findSensorsByDevice(Device deviceId);
    Sensor getSensorBySensorId(int sensorId);
    List<Sensor> getAllSensor();
}
