package com.ipn.upiicsa.proy.sstr.timereportservice.service;

import com.ipn.upiicsa.proy.sstr.timereportservice.dto.CreateReportRequest;
import com.ipn.upiicsa.proy.sstr.timereportservice.entity.Report;

import java.util.List;

public interface ReportService {

    Report create(CreateReportRequest report);
    List<Report> findAllByOwner();
}
