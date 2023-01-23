package com.ipn.upiicsa.proy.sstr.userservice.service.impl;

import com.ipn.upiicsa.proy.sstr.userservice.exception.NotFoundException;
import com.ipn.upiicsa.proy.sstr.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final String NOT_FOUND_ERROR_MSG = "User was not found";

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR_MSG));
    }
}
