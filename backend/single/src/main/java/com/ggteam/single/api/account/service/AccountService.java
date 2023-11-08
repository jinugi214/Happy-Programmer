package com.ggteam.single.api.account.service;

import com.ggteam.single.api.account.dto.PasswordDto;
import com.ggteam.single.api.account.entity.Account;
import com.ggteam.single.api.account.Role;
import com.ggteam.single.api.account.dto.AccountDto;
import com.ggteam.single.api.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
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

    @Transactional
    public ResponseEntity<?> myAccount(String username) {
        Account myAccount = accountRepository.findByUsername(username).orElseThrow(
                () -> new NoSuchElementException("존재하지 않는 아이디입니다."));
        AccountDto accountDto = AccountDto.builder()
                .username(myAccount.getUsername())
                .nickname(myAccount.getNickname())
                .language(myAccount.getLanguage())
                .build();
        return ResponseEntity.ok(accountDto);
    }

    @Transactional
    public ResponseEntity<?> editAccount(AccountDto accountDto) {
        Account myAccount = accountRepository.findByUsername(accountDto.getUsername()).orElseThrow(
                () -> new NoSuchElementException("해당 id의 account가 존재하지 않습니다."));

        if (myAccount == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("아이디를 입력해주세요.");
        }

        if (accountDto.getNickname() != null) myAccount.updateNickname(accountDto.getNickname());
        if (accountDto.getLanguage() != null) myAccount.updateLanguage(accountDto.getLanguage());
        accountRepository.save(myAccount);
        return ResponseEntity.ok("Edit Complete");
    }

    @Transactional
    public ResponseEntity<?> changePassword(PasswordDto passwordDto) {
        Account account = accountRepository.findByUsername(passwordDto.getUsername()).orElseThrow(
                () -> new NoSuchElementException("해당 아이디가 존재하지 않습니다."));

        if (!passwordEncoder.matches(passwordDto.getCurPassword(), account.getPassword())) {
            throw new IllegalArgumentException("현재 비밀번호가 일치하지 않습니다.");
        }

        if (passwordEncoder.matches(passwordDto.getCurPassword(), account.getPassword())) {
            throw new IllegalArgumentException("바꾸려는 비밀번호는 현재 비밀번호와 달라야합니다.");
        }

        account.updatePassword(passwordDto.getNewPassword(), passwordEncoder);
        accountRepository.save(account);
        return ResponseEntity.ok("비밀번호를 변경했습니다.");
    }

//    @Transactional
//    public
}
