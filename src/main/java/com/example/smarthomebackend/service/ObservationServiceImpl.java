package com.example.smarthomebackend.service;

import com.example.smarthomebackend.model.Observation;
import com.example.smarthomebackend.repository.ObservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObservationServiceImpl implements ObservationService {

    @Autowired
    ObservationRepository observationRepository;

    @Override
    public void addObservation(Observation observation) {
        observationRepository.save(observation);
    }
}
