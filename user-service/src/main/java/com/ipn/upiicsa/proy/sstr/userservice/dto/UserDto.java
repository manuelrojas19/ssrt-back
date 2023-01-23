package com.ipn.upiicsa.proy.sstr.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class UserDto implements Serializable {

    @Null
    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotBlank
    @Size(max = 16)
    private String name;

    @NotBlank
    @Size(max = 16)
    private String surname;

    @NotBlank
    @Email
    private String email;

    @Null
    private Timestamp createdAt;

    @Null
    private Timestamp updatedAt;

    @Null
    private String createdBy;

    @Null
    private String updatedBy;
}
