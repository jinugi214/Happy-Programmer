package com.ggteam.single.api.account.controller;

import com.ggteam.single.api.account.dto.AccountDto;
import com.ggteam.single.api.account.dto.LoginRequestDto;
import com.ggteam.single.api.account.dto.PasswordDto;
import com.ggteam.single.api.account.jwt.service.JwtService;
import com.ggteam.single.api.account.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

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

    @GetMapping("/my/account")
    @Operation(summary = "내 계정 정보 확인", description = "로그인 상태(토큰필요)에서 요청 시 정보가져옴")
    public ResponseEntity<?> myAccount(Principal principal) {
        return accountService.myAccount(principal);
    }

    @PutMapping("/edit/account")
    @Operation(summary = "계정 정보 수정", description = "JSON으로 나의 아이디(username), 바꾸려는 닉네임(nickname)," +
            " 사용 언어(language) 필요, 모두 String")
    public ResponseEntity<?> editAccount(@RequestBody AccountDto accountDto) {
        return accountService.editAccount(accountDto);
    }

    @PutMapping("/edit/password")
    @Operation(summary = "비밀번호 변경", description = "JSON으로 이이디(username), 원래 비밀번호(curPassword), " +
            "변경할 비밀번호(newPassword) 필요, 모두 String")
    public ResponseEntity<?> passwordChange(@RequestBody PasswordDto passwordDto) {
        return accountService.changePassword(passwordDto);
    }

    @GetMapping("/check/username/{username}")
    @Operation(summary = "아이디 중복 체크", description = "아이디(username, String)을 입력 시, 중복이라면 400 BAD_REQUSET, " +
            "아니라면 200 OK 반환")
    public ResponseEntity<?> checkUsername(@PathVariable String username) {
        return accountService.checkUsername(username);
    }

    @GetMapping("/check/nickname/{nickname}")
    @Operation(summary = "닉네임 중복 체크", description = "닉네임(nickname, String)을 입력 시, 중복이라면 400 BAD_REQUSET, " +
            "아니라면 200 OK 반환")
    public ResponseEntity<?> checkNickname(@PathVariable String nickname) {
        return accountService.checkNickname(nickname);
    }

    @DeleteMapping("/my/quit")
    @Operation(summary = "회원탈퇴", description = "회원 탈퇴하는 기능. 로그인 상황에서 요청 시 가능")
    public ResponseEntity<?> deleteAccount(Principal principal) {
        return accountService.deleteAccount(principal);
    }
}
