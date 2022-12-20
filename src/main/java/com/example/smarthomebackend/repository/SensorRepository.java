package com.example.smarthomebackend.repository;

import com.example.smarthomebackend.model.Device;
import com.example.smarthomebackend.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {
    @Query("SELECT MAX(id) FROM Sensor")
    String getMaxId();
    @Query("SELECT u FROM Sensor u WHERE u.deviceId = ?1")
    List<Sensor> findSensorsByDeviceId(int deviceId);
}
