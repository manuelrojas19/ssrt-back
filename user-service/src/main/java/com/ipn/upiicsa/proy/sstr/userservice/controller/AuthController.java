package com.ipn.upiicsa.proy.sstr.userservice.controller;

import com.ipn.upiicsa.proy.sstr.userservice.dto.LoginRequestDto;
import com.ipn.upiicsa.proy.sstr.userservice.service.AuthService;
import com.ipn.upiicsa.proy.sstr.userservice.dto.StudentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * Endpoint que realiza el login de un usuario en el sistema
     * @param request Objeto con las credenciales del usuario.
     * @return ResponseEntity con el status de la solicitud.
     */
    @PostMapping("login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto request) {
        return ResponseEntity.ok(authService.login(request));
    }

    /**
     * Endpoint que permite a un usuario registrarse en la aplicación de servicio social.
     * @param user datos del usuario a registrarse.
     * @return ResponseEntity con el status de la solicitud.
     */
    @PostMapping("/signup")
    public ResponseEntity<?> subscriberSignup(@Valid @RequestBody StudentDto user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signup(user));
    }

}
