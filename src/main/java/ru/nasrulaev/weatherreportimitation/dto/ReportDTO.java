package ru.nasrulaev.weatherreportimitation.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ReportDTO {
    @Min(value = -100, message = "Air temperature value should not be below -100")
    @Max(value = 100, message = "Air temperature should not be above 100")
    private Double temperature;

    @NotNull
    private boolean isRaining;

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

    public boolean isRaining() {
        return isRaining;
    }

    public void setRaining(boolean raining) {
        isRaining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
