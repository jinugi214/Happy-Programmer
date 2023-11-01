package com.ggteam.single.api.account.controller;

import com.ggteam.single.api.account.dto.AccountSignUpDto;
import com.ggteam.single.api.account.service.AccountService;
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

    @PostMapping("/account/sign-up")
    public ResponseEntity<?> signUp(@RequestBody AccountSignUpDto signUpDto) {
        System.out.println("sibal");
        log.debug("siuuuuuuuuuuuu");
        accountService.signUp(signUpDto);
        return ResponseEntity.ok("회원가입 성공");
    }

    @GetMapping("/jwt-test")
    public String jwtTest() {
        return "jwt Test 성공";
    }
}
