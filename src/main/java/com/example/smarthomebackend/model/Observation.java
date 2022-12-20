package com.example.smarthomebackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity(name = "observations")
public class Observation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "sensor_id")
    private int sensorId;

    @Column(name = "value")
    @JsonProperty("value")
    private float value;

    @Column(name = "logical_value")
    @JsonProperty("logicalValue")
    private boolean logicalValue;

    @Column(name = "creation_dt")
    private Date creationDt;

    public Observation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public boolean isLogicalValue() {
        return logicalValue;
    }

    public void setLogicalValue(boolean logicalValue) {
        this.logicalValue = logicalValue;
    }

    public Date getCreationDt() {
        return creationDt;
    }

    public void setCreationDt(Date creationDt) {
        this.creationDt = creationDt;
    }

    @Override
    public String toString() {
        return "Observation{" +
                "id=" + id +
                ", value=" + value +
                ", logicalValue=" + logicalValue +
                ", creationDt=" + creationDt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Observation that = (Observation) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
