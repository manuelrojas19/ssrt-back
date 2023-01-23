package com.ipn.upiicsa.proy.sstr.timereportservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@ToString
@Document
public class TimeEntry {

    private ObjectId id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime entryTime;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime exitTime;

    @Max(4)
    private Integer workedHours;

}
