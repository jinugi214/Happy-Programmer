package com.ggteam.single.api.account.handler;

import com.ggteam.single.api.account.exception.InvalidTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class TokenValidationHandler {

    @ExceptionHandler(InvalidTokenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public Map<String, String> handleInvalidTokenException(InvalidTokenException e) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put(e.getErrorCode(), e.getMessage());
        return errorResponse;
    }
}
