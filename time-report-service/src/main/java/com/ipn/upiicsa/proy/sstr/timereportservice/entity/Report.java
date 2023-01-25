package com.ipn.upiicsa.proy.sstr.timereportservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@ToString
@Document
public class Report {

    @Id
    private ObjectId objectId;

    private String owner;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate from;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate to;

    private List<TimeEntry> timeEntries;

    public Integer getTotalHours() {
        if (Objects.isNull(timeEntries))
            return 0;
        return timeEntries
                .stream()
                .map(TimeEntry::getWorkedHours)
                .reduce(0, Integer::sum);
    }
}
