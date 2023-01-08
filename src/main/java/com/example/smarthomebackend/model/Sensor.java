package com.example.smarthomebackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "sensors")
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "device_id")
    private Device device;

    @Column(name = "unit")
    @JsonProperty("unit")
    private String unit;

    @Column(name = "model")
    @JsonProperty("model")
    private String model;

    @Column(name = "type")
    @JsonProperty("type")
    private String type;

    public Sensor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonBackReference
    public Device getDevice() {
        return device;
    }

    public void setDevice(Device deviceId) {
        this.device = deviceId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sensor sensor = (Sensor) o;
        return id == sensor.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
