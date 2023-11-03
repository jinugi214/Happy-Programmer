package com.ggteam.single.api.account.jwt.filter;

import com.ggteam.single.api.account.entity.Account;
import com.ggteam.single.api.account.jwt.service.JwtService;
import com.ggteam.single.api.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationProcessingFilter extends OncePerRequestFilter {

    private static final String NO_CHECK_URL = "/api/account/login";  // "/api/account"로 들어오는 요청은 Filter 작동 X

    private final JwtService jwtService;
    private final AccountRepository accountRepository;

    private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        if (request.getRequestURI().startsWith(NO_CHECK_URL)) {
            filterChain.doFilter(request, response);  // 위의 요청이 온다면 다음 필터 호출
            return;  // return 으로 이후 현재 필터의 진행 막기
        }

        // 사용자 요청이 헤더에서 RefreshToken 추출
        // -> RefreshToken이 없거나 유효하지 않다면(DB에 저장된 RefreshToken과 다르다면) null을 반환
        // 사용자의 요청 헤더에 RefreshToken이 있는 경우는, AccessToken이 만료되어 요청한 경우 밖에 없다.
        // 따라서 위의 경우를 제외하면 추출한 refreshToken은 모두 null
        String refreshToken = jwtService.extractRefreshToken(request)
                .filter(jwtService::isTokenValid)
                .orElse(null);

        // RefreshToken이 요청 헤더에 존재한다면, 사용자의 AccessToken이 만료됐다는 의미
        // -> 같이 받은 RefreshToken가 DB의 RefreshToken과 일치하는지 확인하고 일치하면 AccessToken을 재발급
        if (refreshToken != null) {
            checkRefreshTokenAndReIssueAccessToken(response, refreshToken);
        }
    }

    // 인증 허가 메서드
    // 파라미터의 유저 : 우리가 만든 회원 객체 / 빌더의 유저 : UserDetails의 Account 객체
    //
    // new AccountIdPasswordAuthenticationToken() 으로 인증 객체인 Authenticaiton 객체 생성
    // AccountIdPasswordAuthenticationToken의 파라미터
    // 1. 위에서 만든 UserDetailsAccount 객체 (유저 정보)
    // 2. credential(보통 비밀번호로, 인증 시에는 보통 null로 제거)
    // 3. Collection < ? extends GrantedAuthority>로, UserDetails의 Account 객체 안에 Set<GrantedAuthority> authorities가
    // 있어서 getter로 호출한 후에 new NullAuthoritiesMapper()로 GrantedAuthoritiesMapper 객체를 생성, mapAuthorities()에 담기
    //
    // SecurityContextHolder.getContext()로 SecurityContext를 꺼낸 후,
    // setAuthentication()을 이용해서 위에서 만든 Authentication 객체에 대한 인증 허가 처리
    public void saveAuthentication(Account myAccount) {
        String password = myAccount.getPassword();

        UserDetails userDetailsAccount = org.springframework.security.core.userdetails.User.builder()
                .username(myAccount.getAccountId())
                .password(password)
                .roles(myAccount.getRole().name())
                .build();

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(userDetailsAccount, null,
                        authoritiesMapper.mapAuthorities(userDetailsAccount.getAuthorities()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    // RefreshToken 으로 유저 정보 찾기 & AccessToken / RefreshToken 재발급 메서드
    // 파라미터로 들어온 헤더에서 추출한 리프레시 토큰으로 DB에서 유저를 찾고, 해당 유저가 있다면
    // JwtService.createAccessToken() 으로 AccessToken 생성
    // reIssueRefreshToken() 으로 RefreshToken 재발급 & DB 에 RefreshToken 업데이트 메소드 호출
    // 그 후 JwtService.sendAccessTokenAndRefreshToken() 으로 응답 헤더 보내기
    public void checkRefreshTokenAndReIssueAccessToken(HttpServletResponse response, String refreshToken) {
        accountRepository.findByRefreshToken(refreshToken)
                .ifPresent(account -> {
                    String reIssuedRefreshToken = reIssueRefreshToken(account);
                    jwtService.sendAccessAndRefreshToken(response, jwtService.createAccessToken(account.getAccountId()),
                            reIssuedRefreshToken);
                });
    }

    // RefreshToken 재발급 & DB에 RefreshToken 업데이트 메서드
    // jwtService.createRefreshToken()으로 리프레시 토큰 재발급 후
    // DB에 재발급한 리프레시 토큰 업데이트 후 Flush
    private String reIssueRefreshToken(Account account) {
        String reIssuedRefreshToken = jwtService.createRefreshToken();
        account.updateRefreshToken(reIssuedRefreshToken);
        accountRepository.saveAndFlush(account);
        return reIssuedRefreshToken;
    }

    // AccessToken 체크 & 인증 처리 메서드
    // request 에서 extractAccessToken()으로 AccessToken 추출 후, isTokenValid() 로 유효한 토큰인지 검증
    // 유효한 토큰이면, AccessToken에서 extractAccountId로 AccountId를 추출한 후 findByAccountId()로 해당 아이디를 사용하는 유저 객체 반환
    // 그 유저 객체를 saveAuthentication()으로 인증 처리하여
    // 인증 허기 처리된 객체를 SecurityContextHolder에 담은 후 다음 인증 필터로 진행
    public void checkAccessTokenAndAuthentication(HttpServletRequest request, HttpServletResponse response,
                                                  FilterChain filterChain) throws ServletException, IOException {
        log.info("checkAccessTokenAndAuthentication() 호출");
        jwtService.extractAccessToken(request)
                .filter(jwtService::isTokenValid)
                .ifPresent(accessToken -> jwtService.extractAccountId(accessToken)
                        .ifPresent(accountId -> accountRepository.findByAccountId(accountId)
                                .ifPresent(this::saveAuthentication)));
    }

}
