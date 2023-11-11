package com.ggteam.single.api.save.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ggteam.single.api.account.entity.Account;
import com.ggteam.single.api.account.repository.AccountRepository;
import com.ggteam.single.api.guide.dto.res.ItemWithFavoriteResponse;
import com.ggteam.single.api.guide.entity.Inventory;
import com.ggteam.single.api.guide.repository.InventoryRepository;
import com.ggteam.single.api.save.dto.LoadResponse;
import com.ggteam.single.api.save.dto.SaveRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SaveService {

	private final AccountRepository accountRepository;
	private final InventoryRepository inventoryRepository;

	@Transactional(readOnly = true)
	public LoadResponse loadGame(String username) {
		Account account = accountRepository.findByUsername(username)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계정입니다."));
	}

	public SaveRequest saveGame(String username) {
		Account account = accountRepository.findByUsername(username)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계정입니다."));
	}
}