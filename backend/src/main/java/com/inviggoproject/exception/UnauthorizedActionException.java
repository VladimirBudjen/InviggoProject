package com.inviggoproject.exception;

public class UnauthorizedActionException extends RuntimeException{
    public UnauthorizedActionException(String s) {
        super(s);
    }
}
