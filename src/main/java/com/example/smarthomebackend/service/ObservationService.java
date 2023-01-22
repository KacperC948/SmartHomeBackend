package com.example.smarthomebackend.service;

import com.example.smarthomebackend.model.Observation;

import java.util.Date;
import java.util.List;

public interface ObservationService {
    void addObservation(Observation observation);
    List<Observation> getAllObservations();
    List<Observation> getAllObservationsFromSensor(int sensorId);
    List<Observation> getAllObservationsForSensorFromTimespan(int sensorId, Date startDate, Date endDate);
    List<Observation> getAllObservationGreaterThan(int sensorId, float value);
    Observation getLastObservationFromSensor(int sensorId);
    Observation getLastDateOfRainDetected(int sensorId, boolean logicalValue);
}
