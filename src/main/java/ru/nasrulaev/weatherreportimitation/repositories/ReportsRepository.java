package ru.nasrulaev.weatherreportimitation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nasrulaev.weatherreportimitation.models.Report;

@Repository
public interface ReportsRepository extends JpaRepository<Report, Integer> {
}
