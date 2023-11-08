package com.ggteam.single.api.account.jwt.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ggteam.single.api.account.repository.AccountRepository;
import io.jsonwebtoken.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Getter
@Slf4j
@RequiredArgsConstructor
public class JwtService {

    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.access.expiration}")
    private Long accessTokenExpirationPeriod;

    @Value("${jwt.refresh.expiration}")
    private Long refreshTokenExpirationPeriod;

    @Value("${jwt.access.header}")
    private String accessHeader;

    @Value("${jwt.refresh.header}")
    private String refreshHeader;

    // JWT의 Subject와 Claim으로 username를 사용 ->: 클레임의 name을 "username"로 설정
    // JWT의 헤더에 들어오는 값 : 'Authorization(Key) = Bearer (토큰) (Value)' 형식

    private static final String ACCESS_TOKEN_SUBJECT = "AccessToken";
    private static final String REFRESH_TOKEN_SUBJECT = "RefreshToken";
    private static final String USERNAME_CLAIM = "username";
    private static final String BEARER = "Bearer ";

    private final AccountRepository accountRepository;

    // AccessToken 생성 메서드
    public String createAccessToken(String username) {
        Date now = new Date();
        return JWT.create() // JWT 토큰을 생성하는 빌더 반환
                .withSubject(ACCESS_TOKEN_SUBJECT) // JWT의 Subject 지정 -> AccessToken이므로 AccessToken
                .withExpiresAt(new Date(now.getTime() + accessTokenExpirationPeriod)) // 토큰 만료 시간 설정
                .withClaim(USERNAME_CLAIM, username)
                .sign(Algorithm.HMAC512(secretKey)); // HMAC512 알고리즘 사용, application-jwt.yml에서 지정한 secret 키로 암호화
    }

    // RefreshToken 생성 메서드
    // refreshToken은 Claim에 username을 넣지 않으므로 withClaim() X
    public String createRefreshToken() {
        Date now = new Date();
        return JWT.create()
                .withSubject(REFRESH_TOKEN_SUBJECT)
                .withExpiresAt(new Date(now.getTime() + refreshTokenExpirationPeriod))
                .sign(Algorithm.HMAC512(secretKey));
    }

    // AccessToken 헤더 설정
    public void setAccessTokenHeader(HttpServletResponse response, String accessToken) {
        response.setHeader(accessHeader, accessToken);
    }

    // RefreshToken 헤더 설정
    public void setRefreshTokenHeader(HttpServletResponse response, String refreshToken) {
        response.setHeader(refreshHeader, refreshToken);
    }


    // AccessToken 헤더에 실어서 보내기
    public void sendAccessToken(HttpServletResponse response, String accessToken) {
        response.setStatus(HttpServletResponse.SC_OK);

        response.setHeader(accessHeader, accessToken);
        log.info("(재)발급된 Access Token : {}", accessToken);
    }

    // AccessToken + RefreshToken 헤더에 실어 보내기
    public void sendAccessAndRefreshToken(HttpServletResponse response, String accessToken, String refreshToken) {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        setAccessTokenHeader(response, accessToken);
        setRefreshTokenHeader(response, refreshToken);
        log.info("Access Token, Refresh Token 헤더 설정 완료");
    }

    // 헤더에서 AccessToken 추출
    // 토큰 형식 : Bearer XXX에서 Bearer을 제회하고 순수 토큰만 가져오기 위해서
    // 헤더를 가저온 후 "Bearer"을 삭제(""로 replace)
    public Optional<String> extractAccessToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(accessHeader))
                .filter(accessToken -> accessToken.startsWith(BEARER))
                .map(accessToken -> accessToken.replace(BEARER, ""));
    }

    // 헤더에서 RefreshToken 추출
    // 토큰 형식 : Bearer XXX에서 Bearer을 제회하고 순수 토큰만 가져오기 위해서
    // 헤더를 가저온 후 "Bearer"을 삭제(""로 replace)
    public Optional<String> extractRefreshToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(refreshHeader))
                .filter(refreshToken -> refreshToken.startsWith(BEARER))
                .map(refreshToken -> refreshToken.replace(BEARER, ""));
    }

    // AccessToken 에서 username 추출
    // 추출 전에 JWT.require()로 검증기 생성 후 verify로 AccessToken 검증 후
    // 유효하다면 getClaim() 으로 아이디를 추출
    // 유효하지 않다면 빈 Optional 객체 반환
    public Optional<String> extractUsername(String accessToken) {
        try {
            return Optional.of(
                    JWT.require(Algorithm.HMAC512(secretKey))
                            .build()
                            .verify(accessToken)
                            .getClaim(USERNAME_CLAIM)
                            .asString()
            );
        } catch (Exception e) {
            log.error("Error extracting username from Access Token");
            return Optional.empty();
        }
    }

    // RefreshToken DB 업데이트(저장)
    public void updateRefreshToken(String username, String refreshToken) {
        accountRepository.findByUsername(username)
                .ifPresentOrElse(
                        account -> account.updateRefreshToken(refreshToken),
                        () -> new Exception("일치하는 회원이 없습니다.")
                );
    }

    public boolean isTokenValid(String token) {
        byte[] secretKeyBytes = getSecretKey().getBytes(StandardCharsets.UTF_8);


        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKeyBytes)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return true;
        } catch (SecurityException e) {
            log.info("Invalid JWT signature.");
            throw new JwtException("Auth003");  // 유효하지 않은 서명
        } catch (MalformedJwtException e) {
            log.info("Invalid JWT token.");
            throw new JwtException("Auth004");  // 유효하지 않은 토큰
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token.");
            if (accountRepository.findByRefreshToken(token).isPresent()) {
                throw new JwtException("Auth007");  // Refresh Token 만료
            } else {
                throw new JwtException("Auth001");  // Access Token 만료
            }
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token.");
            throw new JwtException("Auth002");  // 지원하지 않는 토큰
        } catch (IllegalArgumentException e) {
            log.info("JWT token compact of handler are invalid.");
            throw new JwtException("JWT token compact of handler are invalid!!!!");
        }
    }

    public void setErrorResponse(HttpServletRequest request, HttpServletResponse response, Throwable e)
            throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        final Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("error", "Unauthorized");
        errorBody.put("errorCode", e.getMessage());
        errorBody.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        errorBody.put("path", request.getServletPath());

        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getOutputStream(), errorBody);
    }

}
