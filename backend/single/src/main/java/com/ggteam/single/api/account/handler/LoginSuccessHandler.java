package com.ggteam.single.api.account.handler;

import com.ggteam.single.api.account.jwt.service.JwtService;
import com.ggteam.single.api.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequiredArgsConstructor
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtService jwtService;
    private final AccountRepository accountRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) {
        String username = extractUsername(authentication);  // 인증 정보에서 Username(accountId) 추출
        String accessToken = jwtService.createAccessToken(username);  // JwtService의 메서드로 AccessToken을 발급.
        String refreshToken = jwtService.createRefreshToken();  // JwtService의 메서드로 RefreshToken 발급.

        jwtService.sendAccessAndRefreshToken(response, accessToken, refreshToken);  // 응답 헤더에 둘을 담아서 전송

        accountRepository.findByUsername(username)
                .ifPresent(account -> {
                    account.updateRefreshToken(refreshToken);
                    accountRepository.saveAndFlush(account);
                });

        log.info("로그인에 성공하였습니다. 계정 : {}", username);
    }

    private String extractUsername(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }
}
