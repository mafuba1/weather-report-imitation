package ru.nasrulaev.weatherreportimitation.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.nasrulaev.weatherreportimitation.dto.ReportDTO;
import ru.nasrulaev.weatherreportimitation.dto.ReportsList;
import ru.nasrulaev.weatherreportimitation.models.Report;
import ru.nasrulaev.weatherreportimitation.services.ReportsService;
import ru.nasrulaev.weatherreportimitation.util.errors.ReportErrorResponse;
import ru.nasrulaev.weatherreportimitation.util.errors.ReportNotSavedException;
import ru.nasrulaev.weatherreportimitation.validation.ReportValidator;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/reports")
public class ReportsController {

    private final ReportsService reportsService;
    private final ModelMapper modelMapper;
    private final ReportValidator reportValidator;

    @Autowired
    public ReportsController(ReportsService reportsService, ModelMapper modelMapper, ReportValidator reportValidator) {
        this.reportsService = reportsService;
        this.modelMapper = modelMapper;
        this.reportValidator = reportValidator;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> report(@RequestBody @Valid ReportDTO reportDTO,
                                             BindingResult bindingResult) {
        reportValidator.validate(convertToReport(reportDTO), bindingResult);

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

    @ResponseBody
    @GetMapping
    public ReportsList index() {
        return new ReportsList(
                reportsService.index()
                        .stream()
                        .map(this::convertToReportDTO)
                        .collect(Collectors.toList())
        );
    }

    @ResponseBody
    @GetMapping("/rainyDaysCount")
    public int rainyDaysCount() {
        return reportsService.rainyDaysCount();
    }

    @ExceptionHandler
    public ResponseEntity<ReportErrorResponse> handleException(ReportNotSavedException e) {
        ReportErrorResponse response = new ReportErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Report convertToReport(ReportDTO reportDTO) {
        return modelMapper.map(reportDTO, Report.class);
    }

    private ReportDTO convertToReportDTO(Report report) {
        return modelMapper.map(report, ReportDTO.class);
    }
}
