package com.ipn.upiicsa.proy.sstr.userservice.service;

import com.ipn.upiicsa.proy.sstr.userservice.dto.LoginRequestDto;
import com.ipn.upiicsa.proy.sstr.userservice.dto.LoginResponseDto;
import com.ipn.upiicsa.proy.sstr.userservice.dto.UserDto;

public interface AuthService {

    UserDto signup(UserDto user);

    LoginResponseDto login(LoginRequestDto LoginRequestDto);

}
