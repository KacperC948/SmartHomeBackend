package com.example.smarthomebackend.controller;

import com.example.smarthomebackend.model.Observation;
import com.example.smarthomebackend.service.ObservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping("/observation")
public class ObservationController {

    @Autowired
    ObservationService observationService;

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public String addObservation(@RequestBody Observation observation) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        observation.setCreationDt(formatter.parse(formatter.format(date)));
        observationService.addObservation(observation);
        return "Observation is added";
    }
}
