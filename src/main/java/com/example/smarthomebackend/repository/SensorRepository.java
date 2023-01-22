package com.example.smarthomebackend.repository;

import com.example.smarthomebackend.model.Device;
import com.example.smarthomebackend.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {
    List<Sensor> findSensorsByDevice(Device deviceId);
    Sensor getSensorById(int sensorId);
}
