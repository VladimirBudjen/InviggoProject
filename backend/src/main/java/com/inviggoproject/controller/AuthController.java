package com.inviggoproject.controller;

import com.inviggoproject.dto.AuthenticationRequestDto;
import com.inviggoproject.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> createAuthenticationToken(@RequestBody AuthenticationRequestDto dto) {
        return new ResponseEntity<>(authService.logIn(dto.getUsername(), dto.getPassword()), HttpStatus.OK);
    }
}
