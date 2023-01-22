package com.example.smarthomebackend.repository;

import com.example.smarthomebackend.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {
    Device findByMacAndModel(String mac, String model);
    Device getDeviceById(int deviceId);
}
