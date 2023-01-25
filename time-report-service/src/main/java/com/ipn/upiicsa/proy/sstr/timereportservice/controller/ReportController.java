package com.ipn.upiicsa.proy.sstr.timereportservice.controller;

import com.ipn.upiicsa.proy.sstr.timereportservice.dto.CreateReportRequest;
import com.ipn.upiicsa.proy.sstr.timereportservice.dto.FindAllReportsResponse;
import com.ipn.upiicsa.proy.sstr.timereportservice.entity.Report;
import com.ipn.upiicsa.proy.sstr.timereportservice.service.ReportService;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/reports")
public class ReportController {

    private final ReportService reportService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<FindAllReportsResponse> findAllByOwner() {
        return new ResponseEntity<>(reportService.findAllByOwner(), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ResponseEntity<Report> findReportById(@PathVariable ObjectId id) {
        return new ResponseEntity<>(reportService.findById(id), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<Report> create(@RequestBody CreateReportRequest request) {
        return new ResponseEntity<>(reportService.create(request), HttpStatus.OK);
    }
}
