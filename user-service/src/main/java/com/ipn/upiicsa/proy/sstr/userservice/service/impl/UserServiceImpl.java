package com.ipn.upiicsa.proy.sstr.userservice.service.impl;

import com.ipn.upiicsa.proy.sstr.userservice.dto.UserResponse;
import com.ipn.upiicsa.proy.sstr.userservice.entity.Student;
import com.ipn.upiicsa.proy.sstr.userservice.repository.UserRepository;
import com.ipn.upiicsa.proy.sstr.userservice.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse findCurrentLoggedUser() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (Objects.isNull(username)) throw new IllegalStateException("User must be authenticated");

        Student user = (Student) userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User was not found"));

        return UserResponse.builder()
                .username(user.getUsername())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .academicProgram(user.getAcademicProgram())
                .semester(user.getSemester())
                .program(user.getSocialService().getProgram())
                .borrower(user.getSocialService().getBorrower())
                .manager(user.getSocialService().getManager())
                .managerCharge(user.getSocialService().getManagerCharge())
                .build();
    }
}
