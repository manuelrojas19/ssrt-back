package com.ipn.upiicsa.proy.sstr.userservice.controller;

import com.ipn.upiicsa.proy.sstr.userservice.dto.*;
import com.ipn.upiicsa.proy.sstr.userservice.service.AuthService;
import com.ipn.upiicsa.proy.sstr.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    /**
     * Endpoint que realiza el login de un usuario en el sistema
     *
     * @param request Objeto con las credenciales del usuario.
     * @return ResponseEntity con el status de la solicitud.
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto request) {
        return ResponseEntity.ok(authService.login(request));
    }

    /**
     * Endpoint que permite a un usuario registrarse en la aplicación de servicio social.
     *
     * @param request datos del usuario a registrarse.
     * @return ResponseEntity con el status de la solicitud.
     */
    @PostMapping("/signup")
    public ResponseEntity<UserDto> subscriberSignup(@Valid @RequestBody CreateUserRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signup(request));
    }

    @GetMapping("/user")
    public ResponseEntity<UserResponse> getCurrentLoggedUser() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findCurrentLoggedUser());
    }

    /**
     * Endpoint que permite a un usuario registrarse en la aplicación de servicio social.
     *
     * @return ResponseEntity con el status de la solicitud.
     */
    @GetMapping("/validate")
    public ResponseEntity<ValidateSignInResponse> validateSignIn() {
        return ResponseEntity.status(HttpStatus.OK).body(authService.validateSignIn());
    }

}
