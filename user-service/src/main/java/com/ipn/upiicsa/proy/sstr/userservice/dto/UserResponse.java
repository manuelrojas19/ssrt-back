package com.ipn.upiicsa.proy.sstr.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    @NotBlank
    private String username;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotBlank
    private String email;

    @NotBlank
    private String academicProgram;

    @NotBlank
    private String semester;

    @NotBlank
    private String program;

    @NotBlank
    private String borrower;

    @NotBlank
    private String manager;

    @NotBlank
    private String managerCharge;
}
