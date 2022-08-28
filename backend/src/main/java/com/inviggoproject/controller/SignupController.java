package com.inviggoproject.controller;

import com.inviggoproject.dto.SignupRequestDto;
import com.inviggoproject.exception.ExceptionUtils;
import com.inviggoproject.model.User;
import com.inviggoproject.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

@RestController
@Validated
@RequestMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
public class SignupController {

    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        User existUser = this.userService.findByUsername(signupRequestDto.getUsername());
        if (existUser != null) {
            var errorMessage = String.format("Username '%s' already exists!", signupRequestDto.getUsername());
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
        userService.save(signupRequestDto.generateUser());
        return new ResponseEntity<>("User created.", HttpStatus.CREATED);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>(ExceptionUtils.extractDefaultMessages(e), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(ExceptionUtils.extractDefaultMessages(e), HttpStatus.BAD_REQUEST);
    }
}
