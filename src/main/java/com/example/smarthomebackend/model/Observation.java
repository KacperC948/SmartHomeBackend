package com.example.smarthomebackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "observations")
public class Observation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "sensor_id")
    @JsonProperty("sensorId")
    private int sensorId;

    @Column(name = "value")
    @JsonProperty("value")
    private Float value;

    @Column(name = "logical_value")
    @JsonProperty("logicalValue")
    private Boolean logicalValue;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm:ss", timezone="CET")
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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Boolean getLogicalValue() {
        return logicalValue;
    }

    public void setLogicalValue(Boolean logicalValue) {
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
