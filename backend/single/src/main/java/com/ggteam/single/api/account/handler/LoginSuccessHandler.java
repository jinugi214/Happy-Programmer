package com.ggteam.single.api.account.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ggteam.single.api.account.dto.LoginResponseDto;
import com.ggteam.single.api.account.jwt.service.JwtService;
import com.ggteam.single.api.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtService jwtService;
    private final AccountRepository accountRepository;
    private final ObjectMapper objectMapper;

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

                    Long accountId = account.getId();
                    try {
                        tokenResponse(response, accessToken, refreshToken, accountId);  // response 객체를 인자로 추가
                    } catch (IOException e) {
                        throw new RuntimeException("Token response failed", e);  // 오류 처리
                    }
                });


        log.info("로그인에 성공하였습니다. 계정 : {}", username);
    }

    private String extractUsername(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }

    private void tokenResponse(HttpServletResponse response, String accessToken,
                               String refreshToken, Long accountId) throws IOException {
        LoginResponseDto loginResponseDto = LoginResponseDto.builder()
                .id(accountId)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        String json = objectMapper.writeValueAsString(loginResponseDto);  // TokenDto를 JSON 문자열로 변환

        response.getWriter().write(json);  // 응답 본문에 JSON 문자열을 쓰기
        response.getWriter().flush();
        response.getWriter().close();
    }
}
