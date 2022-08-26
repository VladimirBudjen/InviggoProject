package com.inviggoproject.service;

import com.inviggoproject.dto.AuthenticationResponseDto;
import com.inviggoproject.model.User;

public interface AuthService {
    AuthenticationResponseDto logIn(String username, String password);
    User getActiveUser();
}
