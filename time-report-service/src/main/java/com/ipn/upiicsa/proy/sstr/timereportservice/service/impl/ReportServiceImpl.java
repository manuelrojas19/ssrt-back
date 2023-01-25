package com.ipn.upiicsa.proy.sstr.timereportservice.service.impl;

import com.ipn.upiicsa.proy.sstr.timereportservice.dto.CreateReportRequest;
import com.ipn.upiicsa.proy.sstr.timereportservice.dto.FindAllReportsResponse;
import com.ipn.upiicsa.proy.sstr.timereportservice.entity.Report;
import com.ipn.upiicsa.proy.sstr.timereportservice.repository.ReportRepository;
import com.ipn.upiicsa.proy.sstr.timereportservice.service.ReportService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@Slf4j
@Service
@AllArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    @Override
    public Report create(CreateReportRequest request) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        if (Objects.isNull(username)) throw new IllegalStateException("User must be authenticated");

        Report report = Report.builder().from(request.getFrom()).to(request.getTo()).owner(username).build();

        reportRepository.findAllByOwner(username).stream()
                .filter(dateFilter(report)).findAny().ifPresent(r -> {
                    throw new IllegalArgumentException("Report dates are in conflict with other registered reports.");
                });

        return reportRepository.save(report);
    }

    private Predicate<Report> dateFilter(Report report) {
        return r -> report.getFrom().isEqual(r.getFrom())
                || report.getTo().isEqual(r.getTo())
                || report.getFrom().isAfter(r.getFrom())
                && report.getFrom().isBefore(r.getTo())
                || report.getTo().isBefore(r.getTo())
                && report.getTo().isAfter(r.getFrom());
    }

    @Override
    public FindAllReportsResponse findAllByOwner() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (Objects.isNull(username)) throw new IllegalStateException("User must be authenticated");
        return FindAllReportsResponse.builder()
                .reports(reportRepository.findAllByOwner(username))
                .build();
    }

    @Override
    public Report findById(ObjectId id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (Objects.isNull(username)) throw new IllegalStateException("User must be authenticated");
        return reportRepository.findById(id).orElseThrow(() -> new RuntimeException("Report was not found"));
    }
}
