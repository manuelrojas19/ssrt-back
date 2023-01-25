package com.ipn.upiicsa.proy.sstr.timereportservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.bson.types.ObjectId;

import javax.validation.constraints.Max;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@ToString
public class TimeEntry {

    private ObjectId id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime entryTime;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime exitTime;

    @Max(4)
    private Integer workedHours;

}
