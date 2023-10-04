package ru.nasrulaev.weatherreportimitation.dto;

import java.util.List;

public class ReportsList {
    private List<ReportDTO> reports;

    public ReportsList(List<ReportDTO> reports) {
        this.reports = reports;
    }

    public List<ReportDTO> getReports() {
        return reports;
    }

    public void setReports(List<ReportDTO> reports) {
        this.reports = reports;
    }
}
