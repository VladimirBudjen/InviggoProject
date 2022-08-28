package com.inviggoproject.exception;

import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

public class ExceptionUtils {
    public static String extractDefaultMessages(Exception e )
    {
        MethodArgumentNotValidException exception = (MethodArgumentNotValidException) e;
        List<ObjectError> allErrors = exception.getBindingResult().getAllErrors();
        StringBuilder errorMessage = new StringBuilder("");
        for( ObjectError error : allErrors )
        {
            errorMessage.append(error.getDefaultMessage()).append(";\n");
        }
        return errorMessage.toString();
    }
}
