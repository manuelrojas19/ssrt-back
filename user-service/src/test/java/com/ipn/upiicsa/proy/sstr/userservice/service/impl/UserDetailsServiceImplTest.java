package com.ipn.upiicsa.proy.sstr.userservice.service.impl;

import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.ipn.upiicsa.proy.sstr.userservice.exception.NotFoundException;
import com.ipn.upiicsa.proy.sstr.userservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public class UserDetailsServiceImplTest {

    private UserDetailsService userService;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserDetailsServiceImpl(userRepository);
    }

    @Test
    @DisplayName("Test: fallo de usuario no encontrado")
    void itShouldThrownNotFoundException() {
        String username = "usuario_no_encontrado";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.loadUserByUsername(username));
    }
}