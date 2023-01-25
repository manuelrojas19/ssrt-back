package com.ipn.upiicsa.proy.sstr.timereportservice.service;

import com.ipn.upiicsa.proy.sstr.timereportservice.dto.CreateReportRequest;
import com.ipn.upiicsa.proy.sstr.timereportservice.dto.FindAllReportsResponse;
import com.ipn.upiicsa.proy.sstr.timereportservice.entity.Report;
import org.bson.types.ObjectId;

import java.util.List;

public interface ReportService {

    Report create(CreateReportRequest report);
    FindAllReportsResponse findAllByOwner();
    Report findById(ObjectId id);
}
