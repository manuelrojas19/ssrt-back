package com.ipn.upiicsa.proy.sstr.timereportservice.service;

import com.ipn.upiicsa.proy.sstr.timereportservice.dto.CreateTimeEntryRequest;
import com.ipn.upiicsa.proy.sstr.timereportservice.entity.TimeEntry;
import org.bson.types.ObjectId;

import java.util.List;

public interface TimeEntryService  {

    TimeEntry create(CreateTimeEntryRequest request);
    List<TimeEntry> findAllByReport(ObjectId reportId);
    TimeEntry update(ObjectId reportId, TimeEntry entity);
}
