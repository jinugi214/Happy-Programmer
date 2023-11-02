package com.ggteam.single.api.account.controller;

import com.ggteam.single.api.account.dto.AccountSignUpDto;
import com.ggteam.single.api.account.dto.LoginRequestDto;
import com.ggteam.single.api.account.dto.LoginResponseDto;
import com.ggteam.single.api.account.jwt.service.JwtService;
import com.ggteam.single.api.account.service.AccountService;
import com.ggteam.single.api.account.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api")
@Slf4j
public class AccountController {

    private final AccountService accountService;
    private final LoginService loginService;
    private final JwtService jwtService;

    @PostMapping("/account/sign-up")
    public ResponseEntity<?> signUp(@RequestBody AccountSignUpDto signUpDto) throws Exception {
        accountService.signUp(signUpDto);
        return ResponseEntity.ok("회원가입 성공");
    }


    @GetMapping("/account/jwt-test")
    public String jwtTest() {
        return "jwt Test 성공";
    }
}
