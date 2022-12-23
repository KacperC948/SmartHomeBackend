package com.example.smarthomebackend.service;

import com.example.smarthomebackend.model.Observation;

import java.util.Date;
import java.util.List;

public interface ObservationService {
    public void addObservation(Observation observation);
    List<Observation> getAllObserations();
    List<Observation> getAllObserationsFromSensor(int sensorId);
    List<Observation> getAllObservationsForSensorFromTimespan(int sensorId, Date startDate, Date endDate);
}
