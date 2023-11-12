package com.ggteam.single.api.save.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ggteam.single.api.account.entity.Account;
import com.ggteam.single.api.account.entity.Character;
import com.ggteam.single.api.account.repository.AccountRepository;
import com.ggteam.single.api.account.repository.CharacterRepository;
import com.ggteam.single.api.guide.entity.Inventory;
import com.ggteam.single.api.guide.repository.InventoryRepository;
import com.ggteam.single.api.save.dto.LoadResponse;
import com.ggteam.single.api.save.dto.SaveRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SaveService {

	private final AccountRepository accountRepository;
	private final CharacterRepository characterRepository;
	private final InventoryRepository inventoryRepository;

	// 게임 세이브 파일 불러오기
	@Transactional(readOnly = true)
	public LoadResponse loadGame(String username) {
		Character character = characterRepository.findByAccount_Username(username)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 캐릭터입니다."));
		List<Inventory> inventoryList = inventoryRepository.findByCharacter(character);
		return new LoadResponse(character, inventoryList);
	}

	// 게임 세이브하기
	@Transactional
	public void saveGame( String username, SaveRequest saveRequest) {
		Character character = characterRepository.findByAccount_Username(username)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 캐릭터입니다."));
		character.updateCharacter(saveRequest.getExp(),
			saveRequest.getLevel(),
			saveRequest.getSavepoint(),
			saveRequest.getPoint(),
			saveRequest.getStoryProgress(),
			saveRequest.getImgPath());
		inventoryRepository.deleteByCharacter(character); // 인벤토리 전체 삭제후 재 생성
		List<Inventory> newInventoryList = saveRequest.getInventoryList();
		for (Inventory inven : newInventoryList){
			inventoryRepository.save(Inventory.builder()
				.count(inven.getCount())
					.isEquipping(inven.isEquipping())
					.character(character)
					.item(inven.getItem()) // 아이템 값 처리 어떻게 하지?
					.build());
		}
	}
}