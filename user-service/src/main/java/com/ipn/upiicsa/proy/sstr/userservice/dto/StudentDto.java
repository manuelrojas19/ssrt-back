package com.ipn.upiicsa.proy.sstr.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto extends UserDto {

    @NotBlank
    @Size(max = 40)
    @Column(columnDefinition = "TEXT", nullable = false)
    private String academicProgram;

    private String semester;

    private SocialServiceDto socialService;
}
