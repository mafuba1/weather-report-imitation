package ru.nasrulaev.weatherreportimitation.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.nasrulaev.weatherreportimitation.dto.ReportDTO;
import ru.nasrulaev.weatherreportimitation.models.Report;
import ru.nasrulaev.weatherreportimitation.services.ReportsService;
import ru.nasrulaev.weatherreportimitation.util.errors.ReportNotSavedException;

import java.util.List;

@Controller
@RequestMapping("/reports")
public class ReportsController {

    private final ReportsService reportsService;
    private final ModelMapper modelMapper;

    @Autowired
    public ReportsController(ReportsService reportsService, ModelMapper modelMapper) {
        this.reportsService = reportsService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> report(@RequestBody @Valid ReportDTO reportDTO,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errorList = bindingResult.getFieldErrors();
            for (FieldError fieldError : errorList) {
                errorMsg.append(fieldError.getField())
                        .append(" - ")
                        .append(fieldError.getDefaultMessage())
                        .append(";");
            }
            throw new ReportNotSavedException(errorMsg.toString());
        }

        reportsService.save(convertToReport(reportDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Report convertToReport(ReportDTO reportDTO) {
        return modelMapper.map(reportDTO, Report.class);
    }
}
