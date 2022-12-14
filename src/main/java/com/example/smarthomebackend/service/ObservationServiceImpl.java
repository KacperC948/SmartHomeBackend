package com.example.smarthomebackend.service;

import com.example.smarthomebackend.model.Observation;
import com.example.smarthomebackend.repository.ObservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Observation> getAllObserations() {
        return observationRepository.findAll();
    }

    @Override
    public List<Observation> getAllObserationsFromSensor(int sensorId) {
        return observationRepository.findAllBySensorId(sensorId);
    }

    @Override
    public List<Observation> getAllObservationsForSensorFromTimespan(int sensorId, Date startDate, Date endDate) {
        return observationRepository.findAllBySensorIdAndCreationDtBetween(sensorId,startDate,endDate);
    }
}
