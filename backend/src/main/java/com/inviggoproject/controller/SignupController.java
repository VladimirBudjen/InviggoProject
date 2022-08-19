package com.inviggoproject.controller;

import com.inviggoproject.dto.SignupRequestDto;
import com.inviggoproject.model.User;
import com.inviggoproject.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
public class SignupController {

    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody SignupRequestDto signupRequestDto) {
        User existUser = this.userService.findByUsername(signupRequestDto.getUsername());
        if (existUser != null) {
            var errorMessage = String.format("Username '%s' already exists!", signupRequestDto.getUsername());
            return new ResponseEntity<>(errorMessage, HttpStatus.METHOD_NOT_ALLOWED);
        }
        userService.save(signupRequestDto.generateUser());
        return new ResponseEntity<>("User created.", HttpStatus.CREATED);
    }
}
