package com.inviggoproject.dto;

import com.inviggoproject.model.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class SignupRequestDto implements Serializable {

    @NotBlank(message = "Username can not be blank")
    private String username;
    @NotBlank(message = "Password can not be blank")
    private String password;
    @Pattern(regexp = "[\\s[0-9]-]*", message = "Phone number must contain only numbers, white space or hyphen")
    @NotBlank(message = "Phone number can not be blank")
    private String phone;

    public User generateUser() {
        return new User(username, password, phone);
    }
}
