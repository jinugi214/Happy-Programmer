package com.ggteam.single.api.account.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ggteam.single.api.account.filter.CustomLoginAuthFilter;
import com.ggteam.single.api.account.handler.LoginFailureHandler;
import com.ggteam.single.api.account.handler.LoginSuccessHandler;
import com.ggteam.single.api.account.jwt.filter.JwtAuthenticationProcessingFilter;
import com.ggteam.single.api.account.jwt.service.JwtService;
import com.ggteam.single.api.account.repository.AccountRepository;
import com.ggteam.single.api.account.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final LoginService loginService;
    private final JwtService jwtService;
    private final AccountRepository accountRepository;
    private final ObjectMapper objectMapper;

    // 로그인 성공 시 호출되는 LoginSuccessJWTProviderHandler 빈 등록
    @Bean
    public LoginSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler(jwtService, accountRepository);
    }

    // 로그인 실패 시 호출되는 LoginFailureHandler 빈 등록
    @Bean
    public LoginFailureHandler loginFailureHandler() {
        return new LoginFailureHandler();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(loginService);
        return new ProviderManager(provider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .formLogin().disable() // FormLogin 사용 X
                .httpBasic().disable() // httpBasic 사용 X
                .csrf().disable() // csrf 보안 사용 X
                .headers().frameOptions().disable()
                .and()

                // 세션 사용하지 않으므로 STATELESS로 설정
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()

                //== URL별 권한 관리 옵션 ==//
                .authorizeRequests()

                // 아이콘, css, js 관련
                // 기본 페이지, css, image, js 하위 폴더에 있는 자료들은 모두 접근 가능
                .antMatchers("/","/css/**","/images/**","/js/**","/favicon.ico").permitAll()
                .antMatchers("/api/account/sign-up").permitAll() // 회원가입 접근 가능
                .anyRequest().authenticated(); // 위의 경로 이외에는 모두 인증된 사용자만 접근 가능

        // 원래 스프링 시큐리티 필터 순서가 LogoutFilter 이후에 로그인 필터 동작
        // 따라서, LogoutFilter 이후에 우리가 만든 필터 동작하도록 설정
        // 순서 : LogoutFilter -> JwtAuthenticationProcessingFilter -> CustomJsonUsernamePasswordAuthenticationFilter
        http.addFilterAfter(customLoginAuthFilter(), LogoutFilter.class);
        http.addFilterBefore(jwtAuthenticationProcessingFilter(), CustomLoginAuthFilter.class);

        return http.build();
    }

    /**
     * CustomJsonUsernamePasswordAuthenticationFilter 빈 등록
     * 커스텀 필터를 사용하기 위해 만든 커스텀 필터를 Bean으로 등록
     * setAuthenticationManager(authenticationManager())로 위에서 등록한 AuthenticationManager(ProviderManager) 설정
     * 로그인 성공 시 호출할 handler, 실패 시 호출할 handler로 위에서 등록한 handler 설정
     */
    @Bean
    public CustomLoginAuthFilter customLoginAuthFilter() {
        CustomLoginAuthFilter customJsonUsernamePasswordLoginFilter
                = new CustomLoginAuthFilter(objectMapper);
        customJsonUsernamePasswordLoginFilter.setAuthenticationManager(authenticationManager());
        customJsonUsernamePasswordLoginFilter.setAuthenticationSuccessHandler(loginSuccessHandler());
        customJsonUsernamePasswordLoginFilter.setAuthenticationFailureHandler(loginFailureHandler());
        return customJsonUsernamePasswordLoginFilter;
    }

    @Bean
    public JwtAuthenticationProcessingFilter jwtAuthenticationProcessingFilter() {
        JwtAuthenticationProcessingFilter jwtAuthenticationFilter = new JwtAuthenticationProcessingFilter(jwtService, accountRepository);
        return jwtAuthenticationFilter;
    }
}
