package com.ipn.upiicsa.proy.sstr.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialServiceDto {

    @NotBlank
    @Size(max = 40)
    private String program;

    @NotBlank
    @Size(max = 40)
    private String borrower;

    @NotBlank
    @Size(max = 40)
    private String manager;

    @NotBlank
    @Size(max = 40)
    private String managerCharge;

}
