package com.ipn.upiicsa.proy.sstr.userservice.service;

import com.ipn.upiicsa.proy.sstr.userservice.dto.*;

public interface AuthService {

    UserDto signup(CreateUserRequestDto request);

    LoginResponseDto login(LoginRequestDto LoginRequestDto);

    ValidateSignInResponse validateSignIn();

}
