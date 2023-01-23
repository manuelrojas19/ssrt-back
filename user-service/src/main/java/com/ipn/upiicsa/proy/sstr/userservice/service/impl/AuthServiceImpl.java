package com.ipn.upiicsa.proy.sstr.userservice.service.impl;

import com.ipn.upiicsa.proy.sstr.userservice.dto.LoginRequestDto;
import com.ipn.upiicsa.proy.sstr.userservice.dto.LoginResponseDto;
import com.ipn.upiicsa.proy.sstr.userservice.dto.UserDto;
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

@Service
public class AuthServiceImpl implements AuthService {

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

    @Override
    @Transactional
    public UserDto signup(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        if (userRepository.existsByUsername(user.getEmail()) || userRepository.existsByUsername(user.getUsername()))
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
                .token(newAccessToken.getTokenValue())
                .build();
    }
}
