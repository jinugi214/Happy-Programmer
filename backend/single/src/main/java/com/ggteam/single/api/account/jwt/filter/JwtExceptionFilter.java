//package com.ggteam.single.api.account.jwt.filter;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.ggteam.single.api.account.jwt.service.JwtService;
//import io.jsonwebtoken.*;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.MediaType;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.util.HashMap;
//import java.util.Map;
//
//@Slf4j
//@AllArgsConstructor
//public class JwtExceptionFilter extends OncePerRequestFilter {
//
//    private final JwtService jwtService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        try {
//            filterChain.doFilter(request, response);
//        } catch (JwtException e) {
//            setErrorResponse(request, response, e);
//        }
//    }
//
//    private void setErrorResponse(HttpServletRequest request, HttpServletResponse response, Throwable e)
//            throws IOException {
//        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//
//        final Map<String, Object> errorBody = new HashMap<>();
//        errorBody.put("status", HttpServletResponse.SC_UNAUTHORIZED);
//        errorBody.put("errorCode", "Unauthorized");
//        errorBody.put("message", e.getMessage());
//        errorBody.put("path", request.getServletPath());
//
//        final ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.writeValue(response.getOutputStream(), errorBody);
//        response.setStatus(HttpServletResponse.SC_OK);
//    }
//
//
//    public Claims getTokenClaims(HttpServletRequest request) {
//        byte[] secretKeyBytes = jwtService.getSecretKey().getBytes(StandardCharsets.UTF_8);
//
//        try {
//            return Jwts.parserBuilder()
//                    .setSigningKey(secretKeyBytes)
//                    .build()
//                    .parseClaimsJws(jwtService.extractAccessToken(request).orElse(null))
//                    .getBody();
//        } catch (SecurityException e) {
//            log.info("Invalid JWT signature.");
//            throw new JwtException("잘못된 JWT 시그니처");
//        } catch (MalformedJwtException e) {
//            log.info("Invalid JWT token.");
//            throw new JwtException("유효하지 않은 JWT 토큰");
//        } catch (ExpiredJwtException e) {
//            log.info("Expired JWT token.");
//            throw new JwtException("토큰 기한 만료");
//        } catch (UnsupportedJwtException e) {
//            log.info("Unsupported JWT token.");
//        } catch (IllegalArgumentException e) {
//            log.info("JWT token compact of handler are invalid.");
//            throw new JwtException("JWT token compact of handler are invalid.");
//        }
//        return null;
//    }
//}
//
