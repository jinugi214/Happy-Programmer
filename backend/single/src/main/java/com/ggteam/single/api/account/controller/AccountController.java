package com.ggteam.single.api.account.controller;

import com.ggteam.single.api.account.dto.AccountSignUpDto;
import com.ggteam.single.api.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/account")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody AccountSignUpDto signUpDto) throws Exception {
        accountService.signUp(signUpDto);
        return ResponseEntity.ok("회원가입 성공");
    }

    @GetMapping("/jwt_test")
    public String jwtTest() {
        return "jwt Test 성공";
    }
}
