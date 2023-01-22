package com.example.smarthomebackend.service;

import com.example.smarthomebackend.model.Device;
import com.example.smarthomebackend.model.Sensor;

import java.util.List;

public interface SensorService {
    List<Sensor> saveSensors(List<Sensor> sensors, Device deviceId);
    List<Sensor> findSensorsByDevice(Device deviceId);
    Sensor getSensorBySensorId(int sensorId);
    List<Sensor> getAllSensor();
}
