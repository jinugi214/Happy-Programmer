package com.ggteam.single.api.account.controller;

import com.ggteam.single.api.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api")
public class CharacterController {

    private final AccountService accountService;

    @PostMapping("/character/sign-in")
    public ResponseEntity<?> signIn() {
        return ResponseEntity.ok("Sign In");
    }
}
