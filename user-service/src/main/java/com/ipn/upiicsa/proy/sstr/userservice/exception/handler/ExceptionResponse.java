package com.ipn.upiicsa.proy.sstr.userservice.exception.handler;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class ExceptionResponse implements Serializable {

    @JsonIgnore
    private static final long serialVersionUID = 7645574770781355007L;

    private String message;
}