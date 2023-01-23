package com.ipn.upiicsa.proy.sstr.timereportservice.service.impl;

import com.ipn.upiicsa.proy.sstr.timereportservice.dto.CreateTimeEntryRequest;
import com.ipn.upiicsa.proy.sstr.timereportservice.entity.Report;
import com.ipn.upiicsa.proy.sstr.timereportservice.entity.TimeEntry;
import com.ipn.upiicsa.proy.sstr.timereportservice.exception.NotFoundException;
import com.ipn.upiicsa.proy.sstr.timereportservice.repository.ReportRepository;
import com.ipn.upiicsa.proy.sstr.timereportservice.service.TimeEntryService;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class TimeEntryServiceImpl implements TimeEntryService {

    private final ReportRepository reportRepository;

    @Override
    public TimeEntry create(CreateTimeEntryRequest request) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        if (Objects.isNull(username)) throw new IllegalStateException("User must be authenticated");

        Report report = reportRepository.findById(request.getReportId())
                .orElseThrow(() -> new NotFoundException("Report associated was not found"));

        TimeEntry timeEntry = TimeEntry.builder()
                .id(ObjectId.get())
                .date(request.getDate())
                .entryTime(request.getEntryTime())
                .exitTime(request.getExitTime())
                .workedHours(request.getWorkedHours())
                .build();

        if (!(timeEntry.getDate().isEqual(report.getFrom())
                || timeEntry.getDate().isEqual(report.getTo())
                || timeEntry.getDate().isAfter(report.getFrom())
                && timeEntry.getDate().isBefore(report.getTo()))) {
            throw new IllegalStateException("Date is out of range of this report");
        }

        if (Objects.isNull(report.getTimeEntries())) {
            report.setTimeEntries(List.of(timeEntry));
            reportRepository.save(report);
            return timeEntry;
        }

        report.getTimeEntries().stream()
                .filter(r -> timeEntry.getDate().isEqual(r.getDate()))
                .findAny()
                .ifPresent(t -> {
                    throw new IllegalArgumentException("Date is in conflict");
                });

        report.getTimeEntries().add(timeEntry);
        reportRepository.save(report);
        return timeEntry;
    }


    @Override
    public List<TimeEntry> findAllByReport(ObjectId reportId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new NotFoundException("Report associated was not found"));
        if (!report.getOwner().equals(username)) {
            throw new IllegalStateException("Forbidden");
        }
        return report.getTimeEntries();
    }

    @Override
    public TimeEntry update(ObjectId reportId, TimeEntry entity) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new NotFoundException("Report associated was not founded"));

        if (!report.getOwner().equals(username)) {
            throw new IllegalStateException("Forbidden");
        }

        TimeEntry timeEntry = report.getTimeEntries().stream()
                .filter(entry -> entry.getId().equals(entity.getId())).findAny()
                .orElseThrow(() -> new NotFoundException("Time entry was not found"));

        timeEntry.setEntryTime(entity.getEntryTime());
        timeEntry.setExitTime(entity.getExitTime());
        timeEntry.setDate(entity.getDate());
        timeEntry.setWorkedHours(entity.getWorkedHours());
        reportRepository.save(report);

        return timeEntry;
    }


}
