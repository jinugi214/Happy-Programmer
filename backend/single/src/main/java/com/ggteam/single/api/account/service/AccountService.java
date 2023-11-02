package com.ggteam.single.api.account.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ggteam.single.api.account.entity.Account;
import com.ggteam.single.api.account.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AccountService {

	private final AccountRepository accountRepository;

	@Transactional(readOnly = true)
	public Account findById(Long accountId){
		Account account = accountRepository.findById(accountId)
			.orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
		return account;
	}
}
