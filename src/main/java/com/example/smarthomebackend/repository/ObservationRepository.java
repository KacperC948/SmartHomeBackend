package com.example.smarthomebackend.repository;

import com.example.smarthomebackend.model.Observation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ObservationRepository extends JpaRepository<Observation, Integer> {
    List<Observation> findAllBySensorId(int sensorId);
    List<Observation> findAllBySensorIdAndCreationDtBetween(int sensorId, Date startDate, Date endDate);
    List<Observation> findObservationBySensorIdAndValueGreaterThanOrderByCreationDtDesc(int sensorId, float value);
    Observation findTopBySensorIdOrderByIdDesc(int sensorId);
    Observation findTopBySensorIdAndLogicalValueEqualsOrderByCreationDtDesc(int sensorId, boolean logicalValue);
}
