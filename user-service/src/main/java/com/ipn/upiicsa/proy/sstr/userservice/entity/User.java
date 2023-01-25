package com.ipn.upiicsa.proy.sstr.userservice.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder
@Table(name = "app_user")
@Inheritance(strategy = InheritanceType.JOINED)
@EntityListeners(AuditingEntityListener.class)
public abstract class User implements UserDetails, Serializable {

    private static final long serialVersionUID = -2674611579892263134L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 16)
    @Column(columnDefinition = "TEXT", nullable = false)
    private String username;

    @NotBlank
    @Column(columnDefinition = "TEXT", nullable = false)
    private String password;

    @NotBlank
    @Size(max = 16)
    @Column(columnDefinition = "TEXT", nullable = false)
    private String name;

    @NotBlank
    @Size(max = 16)
    @Column(columnDefinition = "TEXT", nullable = false)
    private String surname;

    @NotBlank
    @Email
    @Column(columnDefinition = "TEXT", nullable = false, unique = true)
    private String email;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @CreatedBy
    @Column(columnDefinition = "TEXT", updatable = false)
    private String createdBy;

    @LastModifiedBy
    @Column(columnDefinition = "TEXT")
    private String updatedBy;

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public abstract Collection<? extends GrantedAuthority> getAuthorities();

}
