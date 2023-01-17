package com.example.smarthomebackend.controller;

import com.example.smarthomebackend.model.Observation;
import com.example.smarthomebackend.model.Sensor;
import com.example.smarthomebackend.model.User;
import com.example.smarthomebackend.service.DeviceService;
import com.example.smarthomebackend.service.ObservationService;
import com.example.smarthomebackend.service.SensorService;
import com.example.smarthomebackend.service.UserService;
import com.example.smarthomebackend.util.SensorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/observation")
@CrossOrigin(origins = "http://localhost:3000")
public class ObservationController {

    @Autowired
    ObservationService observationService;

    @Autowired
    SensorService sensorService;

    @Autowired
    UserService userService;

    @Autowired
    DeviceService deviceService;

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
        Sensor sensor = sensorService.getSensorBySensorId(sensorId);
        if(sensor.getType().equals("soil")){
            return observationService.getAllObservationGreaterThan(sensorId, 400f);
        }
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

    @GetMapping(value = "/getLastObservation/{sensorId}")
    public Observation getLastObservationFromSensor(@PathVariable int sensorId){
        return observationService.getLastObservationFromSensor(sensorId);
    }

    @GetMapping(value = "/getLastObservation/{userId}/{type}")
    public Observation getUserLastObservationFromSensorType(@PathVariable int userId, @PathVariable String type){
        User user = userService.getUserById(userId);
        List<Sensor> sensorList = SensorUtil.getSensorsFromUser(user);
        Observation observation = new Observation();
        for(Sensor sensor: sensorList){
            if(sensor.getType().equals(type)){
                observation = observationService.getLastObservationFromSensor(sensor.getId());
            }
        }
        return observation;
    }

    @GetMapping(value = "/get/{userId}/{type}")
    public List<Observation> getObservationForUserAndSensorType(@PathVariable int userId, @PathVariable String type) {
        List<Sensor> sensorList = SensorUtil.getSensorsFromUser(userService.getUserById(userId));
        List<Observation> observations = new ArrayList<>();
        for(Sensor sensor: sensorList){
            if(sensor.getType().equals(type)){
                observations = observationService.getAllObserationsFromSensor(sensor.getId());
            }
        }
        return observations;
    }

    @GetMapping(value = "/getAverage/{userId}/{type}")
    public double getAverageObservationForUserAndSensorType(@PathVariable int userId, @PathVariable String type) {
        User user = userService.getUserById(userId);
        List<Sensor> sensorList = SensorUtil.getSensorsFromUser(user);
        List<Observation> observations = new ArrayList<>();
        for(Sensor sensor: sensorList){
            if(sensor.getType().equals(type)){
                observations = observationService.getAllObserationsFromSensor(sensor.getId());
            }
        }

        OptionalDouble sum = observations
                .stream()
                .mapToDouble(a -> a.getValue())
                .average();

        return sum.getAsDouble();
    }

}
