package com.ggteam.single.api.account.handler;

import com.ggteam.single.api.account.exception.TokenException;
import com.ggteam.single.api.account.jwt.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class TokenExceptionHandler {
    private final JwtService jwtService;

    public TokenExceptionHandler(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @ExceptionHandler(TokenException.class)
    public ResponseEntity<Map<String, String>> handleTokenException(TokenException e) {
        return jwtService.errorClassify(e.getErrorCode(), e.getDefinition());
    }
}
