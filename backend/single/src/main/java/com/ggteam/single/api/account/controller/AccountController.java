package com.ggteam.single.api.account.controller;

import com.ggteam.single.api.account.dto.AccountSignUpDto;
import com.ggteam.single.api.account.dto.LoginDto;
import com.ggteam.single.api.account.service.AccountService;
import com.ggteam.single.api.account.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api")
@Slf4j
public class AccountController {

    private final AccountService accountService;
    private final LoginService loginService;

    @PostMapping("/account/sign-up")
    public ResponseEntity<?> signUp(@RequestBody AccountSignUpDto signUpDto) throws Exception {
        accountService.signUp(signUpDto);
        return ResponseEntity.ok("회원가입 성공");
    }

//    @PostMapping("/account/login")
//    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
//        loginService.loadUserByUsername(loginDto.getAccountId());
//    }






    @GetMapping("/account/jwt-test")
    public String jwtTest() {
        System.out.println("이게 안되나");
        return "jwt Test 성공";
    }
}
