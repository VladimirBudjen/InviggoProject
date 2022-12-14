package com.inviggoproject.service.impl;

import com.inviggoproject.dto.AuthenticationResponseDto;
import com.inviggoproject.model.User;
import com.inviggoproject.security.auth.TokenUtils;
import com.inviggoproject.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final TokenUtils tokenUtils;

    public AuthServiceImpl(AuthenticationManager authenticationManager, TokenUtils tokenUtils) {
        this.authenticationManager = authenticationManager;
        this.tokenUtils = tokenUtils;
    }

    @Override
    public AuthenticationResponseDto logIn(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();
        String userName = user.getUsername();
        return new AuthenticationResponseDto(tokenUtils.generateToken(userName), userName) ;
    }

    @Override
    public User getActiveUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
