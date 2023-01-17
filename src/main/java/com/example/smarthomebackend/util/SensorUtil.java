package com.example.smarthomebackend.util;

import com.example.smarthomebackend.model.Device;
import com.example.smarthomebackend.model.Sensor;
import com.example.smarthomebackend.model.User;

import java.util.ArrayList;
import java.util.List;

public abstract class SensorUtil {
    public static List<Sensor> getSensorsFromUser(User user){
        List<Device> devices = user.getDevices();
        List<Sensor> sensorList = new ArrayList<>();
        for(Device device: devices){
            List<Sensor> sensors = device.getSensors();
            for(int i = 0; i  < sensors.size(); i++){
                sensorList.add(sensors.get(i));
            }
        }
        return sensorList;
    }
}
