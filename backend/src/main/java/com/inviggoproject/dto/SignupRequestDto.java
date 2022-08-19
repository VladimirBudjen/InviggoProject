package com.inviggoproject.dto;

import com.inviggoproject.model.User;
import lombok.Data;

import java.io.Serializable;

@Data
public class SignupRequestDto implements Serializable {

    private String username;
    private String password;
    private String phone;

    public User generateUser() {
        return new User(username, password, phone);
    }
}
