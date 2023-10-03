package ru.nasrulaev.weatherreportimitation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nasrulaev.weatherreportimitation.models.Report;
import ru.nasrulaev.weatherreportimitation.repositories.ReportsRepository;
import ru.nasrulaev.weatherreportimitation.repositories.SensorsRepository;

import java.util.Date;
import java.util.List;

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

    public boolean checkSensor(Report report) {
        return sensorsRepository.existsByName(report.getSensor().getName());
    }

    public List<Report> index() {
        return reportsRepository.findAll();
    }


    private void enrichReport(Report report) {
        report.setTimestamp(new Date());
        report.setSensor(sensorsRepository.findByName(report.getSensor().getName()));
    }
}
