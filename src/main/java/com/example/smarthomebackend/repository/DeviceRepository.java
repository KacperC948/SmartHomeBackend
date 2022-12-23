package com.example.smarthomebackend.repository;

import com.example.smarthomebackend.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {
    @Query("SELECT MAX(id) FROM Device")
    public String getMaxId();
    @Query("SELECT u FROM Device u WHERE u.mac = ?1 and u.model= ?2")
    public Device findByMacAndModel(String mac, String model);
    public Device getDeviceById(int devideId);
}
