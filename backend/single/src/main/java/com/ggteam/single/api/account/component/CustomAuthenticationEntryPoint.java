package com.ggteam.single.api.account.component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ggteam.single.api.account.exception.TokenException;
import com.ggteam.single.api.account.jwt.service.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final JwtService jwtService;

    public CustomAuthenticationEntryPoint(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        String token = request.getHeader("Authorization");
        Map<String, String> errorResponse = new HashMap<>();

        if (token != null && !token.isEmpty()) {
            try {
                JWT.require(Algorithm.HMAC512(jwtService.getSecretKey())).build().verify(token);
            } catch (TokenExpiredException e) {
                errorResponse.put("ErrorCode", "Auth001");
            } catch (SignatureVerificationException e) {
                errorResponse.put("ErrorCode", "Auth004");
            }
        } else {
            errorResponse.put("ErrorCode", "Auth003");
        }

        if (!errorResponse.isEmpty()) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(new ObjectMapper().writeValueAsString(errorResponse));
        }
    }
}
