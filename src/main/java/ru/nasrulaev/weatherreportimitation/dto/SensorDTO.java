package ru.nasrulaev.weatherreportimitation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SensorDTO {
    @NotBlank(message = "Sensor name should not be blank")
    @Size(min = 3, max = 30, message = "Sensor name length should be between 3 and 30")
    private String name;

    public SensorDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
