package com.ipn.upiicsa.proy.sstr.userservice.service;

import com.ipn.upiicsa.proy.sstr.userservice.dto.UserResponse;

public interface UserService {
    UserResponse findCurrentLoggedUser();
}
