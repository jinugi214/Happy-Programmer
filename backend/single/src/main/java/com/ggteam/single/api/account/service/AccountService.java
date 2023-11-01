package com.ggteam.single.api.account.service;

import com.ggteam.single.api.account.entity.Account;
import com.ggteam.single.api.account.Role;
import com.ggteam.single.api.account.dto.AccountSignUpDto;
import com.ggteam.single.api.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<String> signUp(AccountSignUpDto signUpDto) {
        if (accountRepository.findByAccountId(signUpDto.getAccountId()).isPresent()) {
//            throw new Exception("이미 존재하는 아이디입니다.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 존재하는 아이디입니다.");
        }
//
//        if (accountRepository.findByNickname(signUpDto.getNickname()).isPresent()) {
//            throw new Exception("이미 존재하는 닉네임입니다.");
//        }

        Account account = Account.builder()
                .accountId(signUpDto.getAccountId())
                .password(signUpDto.getPassword())
                .nickname(signUpDto.getNickname())
                .language(signUpDto.getLanguage())
                .refreshToken(null)
                .role(Role.USER)
                .build();

        account.passwordEncode(passwordEncoder);
        accountRepository.save(account);
        return ResponseEntity.ok(account.getNickname());
    }

}
