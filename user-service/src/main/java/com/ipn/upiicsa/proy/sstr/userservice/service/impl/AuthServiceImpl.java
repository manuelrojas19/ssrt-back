package com.ipn.upiicsa.proy.sstr.userservice.service.impl;

import com.ipn.upiicsa.proy.sstr.userservice.dto.*;
import com.ipn.upiicsa.proy.sstr.userservice.entity.SocialService;
import com.ipn.upiicsa.proy.sstr.userservice.entity.Student;
import com.ipn.upiicsa.proy.sstr.userservice.entity.User;
import com.ipn.upiicsa.proy.sstr.userservice.mapper.UserMapper;
import com.ipn.upiicsa.proy.sstr.userservice.repository.UserRepository;
import com.ipn.upiicsa.proy.sstr.userservice.service.AuthService;
import com.ipn.upiicsa.proy.sstr.userservice.util.JwtTokenUtil;
import com.ipn.upiicsa.proy.sstr.userservice.util.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Service
public class AuthServiceImpl implements AuthService {

    private static final String AUTH_HEADER_NAME = "Authorization";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HttpServletRequest request;

    @Override
    @Transactional
    public UserDto signup(CreateUserRequestDto requestDto) {

        Student user = requestToStudent(requestDto);

        if (userRepository.existsByUsername(user.getEmail()) || userRepository.existsByEmail(user.getEmail()))
            throw new RuntimeException("This user is actually registered in the system.");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    @Transactional(readOnly = true)
    public LoginResponseDto login(LoginRequestDto requestDto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(requestDto.getUsername(),
                        requestDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userRepository.findByUsername(requestDto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Token newAccessToken = jwtTokenUtil.generateAccessToken(user);
        return LoginResponseDto.builder()
                .status("Successful")
                .message("User is login in")
                .user(user.getUsername())
                .token(newAccessToken.getTokenValue())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public ValidateSignInResponse validateSignIn() {

        if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated())
            return ValidateSignInResponse.builder()
                    .authenticated(false)
                    .build();

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        if (username.equals("anonymousUser")) {
            return ValidateSignInResponse.builder()
                    .authenticated(false)
                    .build();
        }

        return ValidateSignInResponse.builder()
                .authenticated(true)
                .jwt(getJwtFromRequest())
                .user(username)
                .build();
    }

    private String getJwtFromRequest() {
        String bearerToken = request.getHeader(AUTH_HEADER_NAME);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            bearerToken = bearerToken.substring(7);
        }
        return bearerToken;
    }

    private Student requestToStudent(CreateUserRequestDto requestDto) {
        return Student.builder()
                .username(requestDto.getUsername())
                .password(requestDto.getPassword())
                .name(requestDto.getName())
                .surname(requestDto.getSurname())
                .email(requestDto.getEmail())
                .academicProgram(requestDto.getAcademicProgram())
                .semester(requestDto.getSemester())
                .socialService(
                        SocialService.builder()
                                .manager(requestDto.getManager())
                                .managerCharge(requestDto.getManagerCharge())
                                .borrower(requestDto.getBorrower())
                                .program(requestDto.getProgram())
                                .build()
                )
                .build();
    }
}
