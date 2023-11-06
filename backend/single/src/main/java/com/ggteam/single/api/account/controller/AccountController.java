package com.ggteam.single.api.account.controller;

import com.ggteam.single.api.account.dto.AccountDto;
import com.ggteam.single.api.account.dto.LoginRequestDto;
import com.ggteam.single.api.account.dto.PasswordDto;
import com.ggteam.single.api.account.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/account")
@Tag(name = "회원", description = "회원 관련 api")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/sign-up")
    @Operation(summary = "회원가입", description = "JSON으로 아이디(accountId), 비밀번호(password)," +
            " 닉네임(nickname), 사용 언어(language) 필요, 모두 String")
    public ResponseEntity<?> signUp(@RequestBody AccountDto signUpDto) throws Exception {
        return accountService.signUp(signUpDto);
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "실제 로그인은 Filter를 통해서 작동. JSON으로 아이디(accountId)," +
            " 비밀번호(password) 필요, 모두 String")
    public void login(@RequestBody LoginRequestDto loginDto) {
        // Swagger 확인용
    }

    @GetMapping("/my-account/{id}")
    @Operation(summary = "내 계정 정보 확인", description = "URL endpoint에 id(username 아님)필요")
    public ResponseEntity<?> myAccount(@PathVariable Long id) {
        return accountService.myAccount(id);
    }

    @PutMapping("/edit-account")
    @Operation(summary = "계정 정보 수정", description = "JSON으로 바꾸려는 닉네임(nickname), 사용 언어(language) 필요, 모두 String")
    public ResponseEntity<?> editAccount(@RequestBody AccountDto accountDto) {
        return accountService.editAccount(accountDto);
    }

    @PutMapping("/change-password")
    @Operation(summary = "비밀번호 변경", description = "JSON으로 이이디(username), 원래 비밀번호(curPassword), " +
            "변경할 비밀번호(newPassword) 필요, 모두 String")
    public ResponseEntity<?> passwordChange(@RequestBody PasswordDto passwordDto) {
        return accountService.changePassword(passwordDto);
    }
}
