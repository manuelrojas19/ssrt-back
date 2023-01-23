package com.ipn.upiicsa.proy.sstr.timereportservice.controller;

import com.ipn.upiicsa.proy.sstr.timereportservice.dto.CreateTimeEntryRequest;
import com.ipn.upiicsa.proy.sstr.timereportservice.entity.TimeEntry;
import com.ipn.upiicsa.proy.sstr.timereportservice.service.TimeEntryService;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/time-entries")
public class TimeEntryController {

    private final TimeEntryService timeEntryService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<List<TimeEntry>> findAllByReport(@PathParam("reportId") ObjectId reportId) {
        return new ResponseEntity<>(timeEntryService.findAllByReport(reportId), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody CreateTimeEntryRequest request) {
        return new ResponseEntity<>(timeEntryService.create(request), HttpStatus.OK);
    }
}
