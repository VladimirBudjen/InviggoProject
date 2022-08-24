package com.inviggoproject.service;

import com.inviggoproject.dto.AuthenticationResponseDto;

public interface AuthService {
    AuthenticationResponseDto logIn(String username, String password);
}
