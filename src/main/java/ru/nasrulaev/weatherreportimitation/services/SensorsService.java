package ru.nasrulaev.weatherreportimitation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nasrulaev.weatherreportimitation.models.Sensor;
import ru.nasrulaev.weatherreportimitation.repositories.SensorsRepository;

@Service
public class SensorsService {
    private final SensorsRepository sensorsRepository;

    @Autowired
    public SensorsService(SensorsRepository sensorsRepository) {
        this.sensorsRepository = sensorsRepository;
    }

    public void save(Sensor sensor){
        sensorsRepository.save(sensor);
    }

    public boolean uniqueCheck(Sensor sensor) {
        return sensorsRepository.existsByName(sensor.getName());
    }
}

