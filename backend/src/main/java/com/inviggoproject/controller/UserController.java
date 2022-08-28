package com.inviggoproject.controller;

import com.inviggoproject.dto.GetUserInfoResponseDto;
import com.inviggoproject.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userName}")
    public ResponseEntity<GetUserInfoResponseDto> getUserInfo(@PathVariable String userName) {
        var user = userService.findByUsername(userName);
        return new ResponseEntity<>(new GetUserInfoResponseDto(user.getPhone(), user.getRegistrationDate()), HttpStatus.CREATED);
    }
}
