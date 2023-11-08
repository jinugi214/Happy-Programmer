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
    private final JwtService jwtService;

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

    @GetMapping("/my-account")
    @Operation(summary = "내 계정 정보 확인", description = "로그인 상태(토큰필요)에서 요청 시 정보가져옴")
    public ResponseEntity<?> myAccount(Principal principal) {
        return accountService.myAccount(principal.getName());
    }

    @PutMapping("/edit-account")
    @Operation(summary = "계정 정보 수정", description = "JSON으로 나의 아이디(username), 바꾸려는 닉네임(nickname)," +
            " 사용 언어(language) 필요, 모두 String")
    public ResponseEntity<?> editAccount(@RequestBody AccountDto accountDto) {
        return accountService.editAccount(accountDto);
    }

    @PutMapping("/change-password")
    @Operation(summary = "비밀번호 변경", description = "JSON으로 이이디(username), 원래 비밀번호(curPassword), " +
            "변경할 비밀번호(newPassword) 필요, 모두 String")
    public ResponseEntity<?> passwordChange(@RequestBody PasswordDto passwordDto) {
        return accountService.changePassword(passwordDto);
    }

    @PostMapping("/new/access-token")
    @Operation(summary = "AccessToken 새로 발급", description = "로그인 된 상태에서 요청만 하면 가능하게 할 예정")
    public ResponseEntity<?> newAccessToken(HttpServletResponse response) {
        String username = "username 설정하는 메서드 필요";
        String accessToken = jwtService.createAccessToken(username);

        response.setHeader("Authorization", "Bearer " + accessToken);
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("accessToken", accessToken);

        return ResponseEntity.ok(responseBody);
    }

    @DeleteMapping("/quit")
    @Operation
    public ResponseEntity<?> deleteAccount() {
        return ResponseEntity.ok("deleted");
    }
}
