package com.example.smarthomebackend.controller;

import com.example.smarthomebackend.model.Sensor;
import com.example.smarthomebackend.model.User;
import com.example.smarthomebackend.service.SensorService;
import com.example.smarthomebackend.service.UserService;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    UserService userService;

    @Autowired
    SensorService sensorService;

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping(value = "/get/{id}", produces = "application/json")
    public User getUserById(@PathVariable int id){
        return userService.getUserById(id);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        JSONObject jsonObject = new JSONObject();
        try {
            if (userService.getUserByMac(user.getMac()) != null) {
                if(user.getUsername().equals(userService.getUserByMac(user.getMac()).getUsername())){
                    jsonObject.put("usernameError", "Username " + user.getUsername() + " is already used");
                    jsonObject.put("passwordError", "User with address MAC " + user.getMac() + " already exist");
                    return new ResponseEntity<>(jsonObject.toString(), HttpStatus.valueOf(403));
                }
                jsonObject.put("message", "User with address MAC " + user.getMac() + " already exist.");
                return new ResponseEntity<>(jsonObject.toString(), HttpStatus.resolve(403));
            } else if(userService.getUserByUsername(user.getUsername()) != null) {
                jsonObject.put("message", "Username " + user.getUsername() + " is already used.");
                return new ResponseEntity<>(jsonObject.toString(), HttpStatus.resolve(403));
            } else {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                User savedUser = userService.addUser(user);
                jsonObject.put("user id", savedUser.getId());
                jsonObject.put("username", savedUser.getUsername());
                jsonObject.put("MAC", savedUser.getMac());
                jsonObject.put("devices", savedUser.getDevices());
                return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
            }
        } catch (JSONException e) {
            try {
                jsonObject.put("exception", e.getMessage());
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
            return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<User> login(@RequestBody User user){
         JSONObject jsonObject = new JSONObject();
         try {
             User userAlreadySaved = userService.getUserByUsername(user.getUsername());
             if(userAlreadySaved != null){
                 if(passwordEncoder.matches(user.getPassword(), userAlreadySaved.getPassword())){
                     User userToReturn = new User();
                     jsonObject.put("message", user.getUsername() + "login successfully");
                     userToReturn.setId(userAlreadySaved.getId());
                     userToReturn.setUsername(userAlreadySaved.getUsername());
                     userToReturn.setMac(userAlreadySaved.getMac());
                     userToReturn.setDevices(userAlreadySaved.getDevices());
                     jsonObject.put("user", userToReturn);
                     return new ResponseEntity<>(userToReturn, HttpStatus.OK);
                 } else {
                     jsonObject.put("message", "password is not correct");
                     return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
                 }
             } else {
                 jsonObject.put("message", "Username or email is incorrect");
                 return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
             }
         } catch (JSONException e){
             try{
                 jsonObject.put("exception", e.getMessage());
             }  catch (JSONException e1) {
                 e1.printStackTrace();
             }
             return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
         }
    }

    @GetMapping(value = "/get/{id}/getSensors", produces = "application/json")
    public List<Sensor> getAllSensorsFromUser(@PathVariable int id){
        User user = userService.getUserById(id);
        //return sensorService.getAllSensorByUser(user);
//        List<Device> devices = user.getDevices();
//        for(Device device : devices){
//            List<Sensor> sensorList = devices.iterator().getSensors();
//            sensors.add(device.getSensors());
//        }
        return null;
    }

}
