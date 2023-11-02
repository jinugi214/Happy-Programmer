package com.ggteam.single.api.account.service;

import com.ggteam.single.api.account.entity.Account;
import com.ggteam.single.api.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String accountId) throws UsernameNotFoundException {
        Account account = accountRepository.findByAccountId(accountId)
                .orElseThrow(() -> new UsernameNotFoundException("해당 계정이 존재하지 않습니다."));

        return org.springframework.security.core.userdetails.User.builder()
                .username(account.getAccountId())
                .password(account.getPassword())
                .roles(account.getRole().name())
                .build();
    }

//    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequestDto.getAccountId(), loginRequestDto.getPassword())
//        );
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        UserDetails userDetails = loadUserByUsername(loginRequestDto.getAccountId());
//        String accessToken = jwtService.createAccessToken(userDetails.getUsername());
//        String refreshToken = jwtService.createRefreshToken();
//
//        jwtService.updateRefreshToken(userDetails.getUsername(), refreshToken);
//
//        LoginResponseDto loginResponseDto = LoginResponseDto.builder()
//                .accessToken(accessToken)
//                .refreshToken(refreshToken)
//                .build();
//
//        return ResponseEntity.ok(loginResponseDto);
//    }
}
