package com.ipn.upiicsa.proy.sstr.timereportservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ipn.upiicsa.proy.sstr.timereportservice.entity.Report;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindAllReportsResponse {
    private List<Report> reports;
}
