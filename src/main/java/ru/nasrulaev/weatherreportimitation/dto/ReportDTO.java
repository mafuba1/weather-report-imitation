package ru.nasrulaev.weatherreportimitation.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import ru.nasrulaev.weatherreportimitation.models.Sensor;

public class ReportDTO {
    @Min(value = -100, message = "Air temperature value should not be below -100")
    @Max(value = 100, message = "Air temperature should not be above 100")
    private Double temperature;

    @NotNull
    private boolean isRaining;

    @NotNull
    private Sensor sensor;

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

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
