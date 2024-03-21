# Backend for smarthome app based on Arduino Mega

**Introduction**

This repo is backend part of SmartHome App with Arduino Mega. This project contains all parts that are required for communication with Arduino Mega and receiving values from sensors and storing them in databes. Backend is written in Java and Spring Boot with MySQL database. System is build with MVC pattern and there are four main models -> Device, Observation, Sensor, User. By API values from sensors from Arduino Mega are stored in database for proper User. There are endopoints for passing values to frontend react app for showing them in dashboard for users.

# Diagram of the entire system

<img src="https://github.com/KacperC948/SmartHomeBackend/assets/59024079/29d8cc84-a5ed-4d5f-90fe-1af0c4063328" alt="drawing" width="80%"/> 
