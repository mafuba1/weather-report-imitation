package ru.nasrulaev.weatherreportimitation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nasrulaev.weatherreportimitation.models.Report;
import ru.nasrulaev.weatherreportimitation.repositories.ReportsRepository;
import ru.nasrulaev.weatherreportimitation.repositories.SensorsRepository;

import java.util.Date;

@Service
public class ReportsService {
    private final ReportsRepository reportsRepository;
    private final SensorsRepository sensorsRepository;

    @Autowired
    public ReportsService(ReportsRepository reportsRepository, SensorsRepository sensorsRepository) {
        this.reportsRepository = reportsRepository;
        this.sensorsRepository = sensorsRepository;
    }

    public void save(Report report) {
        enrichReport(report);
        reportsRepository.save(report);
    }

    private void enrichReport(Report report) {
        report.setTimestamp(new Date());
        report.setSensor(sensorsRepository.findByName(report.getSensor().getName()));
    }
}
