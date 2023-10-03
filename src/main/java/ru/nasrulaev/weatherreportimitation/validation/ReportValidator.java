package ru.nasrulaev.weatherreportimitation.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.nasrulaev.weatherreportimitation.models.Report;
import ru.nasrulaev.weatherreportimitation.services.ReportsService;

@Component
public class ReportValidator implements Validator {

    private final ReportsService reportsService;

    @Autowired
    public ReportValidator(ReportsService reportsService) {
        this.reportsService = reportsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Report.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Report report = (Report) target;

        if (!reportsService.checkSensor(report)) {
            errors.rejectValue("sensor", "", "Sensor with such name does not exist");
        }

    }
}
