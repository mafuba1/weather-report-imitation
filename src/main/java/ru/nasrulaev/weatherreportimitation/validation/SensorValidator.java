package ru.nasrulaev.weatherreportimitation.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.nasrulaev.weatherreportimitation.models.Sensor;
import ru.nasrulaev.weatherreportimitation.services.SensorsService;

@Component
public class SensorValidator implements Validator {

    private final SensorsService sensorsService;

    @Autowired
    public SensorValidator(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Sensor.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;

        if (sensorsService.uniqueCheck(sensor)) {
            errors.rejectValue("name", "", "Sensor with such name already exists");
        }
    }
}
