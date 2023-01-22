package com.example.smarthomebackend.service;

import com.example.smarthomebackend.model.Observation;
import com.example.smarthomebackend.repository.ObservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ObservationServiceImpl implements ObservationService {

    @Autowired
    ObservationRepository observationRepository;

    @Override
    public void addObservation(Observation observation) {
        observationRepository.save(observation);
    }

    @Override
    public List<Observation> getAllObservations() {
        return observationRepository.findAll();
    }

    @Override
    public List<Observation> getAllObservationsFromSensor(int sensorId) {
        return observationRepository.findAllBySensorId(sensorId);
    }

    @Override
    public List<Observation> getAllObservationsForSensorFromTimespan(int sensorId, Date startDate, Date endDate) {
        return observationRepository.findAllBySensorIdAndCreationDtBetween(sensorId,startDate,endDate);
    }

    @Override
    public List<Observation> getAllObservationGreaterThan(int sensorId, float value) {
        List<Observation> observations = new ArrayList<>();
        observations.add(observationRepository.findObservationBySensorIdAndValueGreaterThanOrderByCreationDtDesc(sensorId, value).get(0));
        return observations;
    }

    @Override
    public Observation getLastObservationFromSensor(int sensorId) {
        return observationRepository.findTopBySensorIdOrderByIdDesc(sensorId);
    }

    @Override
    public Observation getLastDateOfRainDetected(int sensorId, boolean logicalValue) {
        return observationRepository.findTopBySensorIdAndLogicalValueEqualsOrderByCreationDtDesc(sensorId, logicalValue);
    }
}
