package com.ipn.upiicsa.proy.sstr.userservice.entity;

import com.ipn.upiicsa.proy.sstr.userservice.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student extends User {

    @NotBlank
    @Size(max = 40)
    @Column(columnDefinition = "TEXT", nullable = false)
    private String academicProgram;

    @Max(14)
    @PositiveOrZero
    private Integer semester;

    @Embedded
    private SocialService socialService;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(Roles.STUDENT.name()));
    }
}
