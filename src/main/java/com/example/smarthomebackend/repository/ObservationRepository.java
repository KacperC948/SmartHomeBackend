package com.example.smarthomebackend.repository;

import com.example.smarthomebackend.model.Observation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObservationRepository extends JpaRepository<Observation, Integer> {

}
