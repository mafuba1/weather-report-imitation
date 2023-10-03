package ru.nasrulaev.weatherreportimitation.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ReportDTO {
    @Min(value = -100, message = "Air temperature value should not be below -100")
    @Max(value = 100, message = "Air temperature should not be above 100")
    @NotNull
    private Double temperature;

    @NotNull
    private Boolean isRaining;

    @NotNull
    private SensorDTO sensor;

    public ReportDTO() {
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Boolean isRaining() {
        return isRaining;
    }

    public void setRaining(Boolean raining) {
        isRaining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }

    @Override
    public String toString() {
        return "ReportDTO{" +
                "temperature=" + temperature +
                ", isRaining=" + isRaining +
                ", sensor=" + sensor +
                '}';
    }
}
