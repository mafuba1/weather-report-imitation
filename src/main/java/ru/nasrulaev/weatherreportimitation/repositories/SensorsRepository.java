package ru.nasrulaev.weatherreportimitation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nasrulaev.weatherreportimitation.models.Sensor;

@Repository
public interface SensorsRepository extends JpaRepository<Sensor, Integer> {
}
