package com.ggteam.single.api.account.service;

import com.ggteam.single.api.account.dto.PasswordDto;
import com.ggteam.single.api.account.entity.Account;
import com.ggteam.single.api.account.Role;
import com.ggteam.single.api.account.dto.AccountDto;
import com.ggteam.single.api.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<String> signUp(AccountDto signUpDto) throws Exception {
        if (accountRepository.findByUsername(signUpDto.getUsername()).isPresent()) {
            throw new Exception("이미 존재하는 아이디입니다.");
        }

        if (accountRepository.findByNickname(signUpDto.getNickname()).isPresent()) {
            throw new Exception("이미 존재하는 닉네임입니다.");
        }

        Account account = Account.builder()
                .username(signUpDto.getUsername())
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

    public ResponseEntity<?> myAccount(Long id) {
        Account myAccount = accountRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("해당 id의 account가 존재하지 않습니다."));
        AccountDto accountDto = AccountDto.builder()
                .username(myAccount.getUsername())
                .nickname(myAccount.getNickname())
                .language(myAccount.getLanguage())
                .build();
        return ResponseEntity.ok(myAccount);
    }

    public ResponseEntity<?> editAccount(AccountDto accountDto) {
        Account myAccount = accountRepository.findByUsername(accountDto.getUsername()).orElseThrow(
                () -> new NoSuchElementException("해당 id의 account가 존재하지 않습니다."));

        myAccount.updateNickname(accountDto.getNickname());
        myAccount.updateLanguage(accountDto.getLanguage());
        accountRepository.save(myAccount);
        return ResponseEntity.ok("Edit Complete");
    }

    public ResponseEntity<?> changePassword(PasswordDto passwordDto) {
        Account account = accountRepository.findByUsername(passwordDto.getUsername()).orElseThrow(
                () -> new NoSuchElementException("해당 아이디가 존재하지 않습니다."));
        if (!passwordEncoder.matches(passwordDto.getCurPassword(), account.getPassword())) {
            throw new IllegalArgumentException("현재 비밀번호가 일치하지 않습니다.");
        }

        account.updatePassword(passwordDto.getNewPassword(), passwordEncoder);
        accountRepository.save(account);
        return ResponseEntity.ok("비밀번호를 변경했습니다.");
    }
}
