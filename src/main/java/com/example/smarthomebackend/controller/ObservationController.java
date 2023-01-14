package com.example.smarthomebackend.controller;

import com.example.smarthomebackend.model.Observation;
import com.example.smarthomebackend.service.ObservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/observation")
@CrossOrigin(origins = "http://localhost:3000")
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

    @GetMapping(value = "/getAll")
    public List<Observation> getllObservations(){
        return observationService.getAllObserations();
    }

    @GetMapping(value = "/get/{sensorId}")
    public List<Observation> getllObservationsFromSensor(@PathVariable int sensorId){
        return observationService.getAllObserationsFromSensor(sensorId);
    }

    @GetMapping(value = "/getAll/{sensorId}/{startDate}/{endDate}")
    public List<Observation> getAllObservationsForSensorFromTimespan(@PathVariable int sensorId, @PathVariable("startDate") @DateTimeFormat(pattern = "") Date startDate, @PathVariable("endDate") @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") Date endDate){
        return observationService.getAllObservationsForSensorFromTimespan(sensorId, startDate, endDate);
    }

    @GetMapping(value = "/getObservation")
    public List<Observation> getAllObservationsForSensorFromTimespan(@RequestBody Map<String, String> observation) throws ParseException {
        String sensorId = observation.get("sensorId");
        String startDate = observation.get("startDate");
        String endDate = observation.get("endDate");
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date startDateAsDate = formatter.parse(startDate);
        Date endDateAsDate = formatter.parse(endDate);
        return observationService.getAllObservationsForSensorFromTimespan(Integer.parseInt(sensorId), startDateAsDate, endDateAsDate);
    }

}
