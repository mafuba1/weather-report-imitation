package ru.nasrulaev.weatherreportimitation.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.util.Date;

@Entity
@Table(name = "report")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "timestamp")
    private Date timestamp;

    @Column(name = "value")
    @Min(value = -100, message = "Air temperature value should not be below -100")
    @Max(value = 100, message = "Air temperature should not be above 100")
    private Double temperature;

    @Column(name = "raining")
    private boolean isRaining;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
