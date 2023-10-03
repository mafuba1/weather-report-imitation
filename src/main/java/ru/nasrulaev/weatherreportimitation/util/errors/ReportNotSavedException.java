package ru.nasrulaev.weatherreportimitation.util.errors;

public class ReportNotSavedException extends RuntimeException {
    public ReportNotSavedException(String msg) {
        super(msg);
    }
}
