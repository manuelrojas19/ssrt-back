package com.ipn.upiicsa.proy.sstr.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SocialService {

    @NotBlank
    @Size(max = 40)
    @Column(columnDefinition = "TEXT", nullable = false)
    private String program;

    @NotBlank
    @Size(max = 40)
    @Column(columnDefinition = "TEXT", nullable = false)
    private String borrower;

    @NotBlank
    @Size(max = 40)
    @Column(columnDefinition = "TEXT", nullable = false)
    private String manager;

    @NotBlank
    @Size(max = 40)
    @Column(columnDefinition = "TEXT", nullable = false)
    private String managerCharge;

}
