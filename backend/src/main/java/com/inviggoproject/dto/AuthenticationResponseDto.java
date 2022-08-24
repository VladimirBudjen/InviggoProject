package com.inviggoproject.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthenticationResponseDto implements Serializable {
    private String jwt;
    private String userName;

    public AuthenticationResponseDto(String jwt, String userName) {
        this.jwt = jwt;
        this.userName = userName;
    }

    public AuthenticationResponseDto() {
    }
}
