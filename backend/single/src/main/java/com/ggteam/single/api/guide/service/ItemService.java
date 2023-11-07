package com.ggteam.single.api.guide.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ggteam.single.api.account.entity.Account;
import com.ggteam.single.api.account.repository.AccountRepository;
import com.ggteam.single.api.guide.dto.req.ItemFavoriteRequest;
import com.ggteam.single.api.guide.dto.res.ItemResponse;
import com.ggteam.single.api.guide.dto.res.ItemWithFavoriteResponse;
import com.ggteam.single.api.guide.entity.Item;
import com.ggteam.single.api.guide.repository.ItemFavoriteRepository;
import com.ggteam.single.api.guide.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ItemService {

	private final ItemRepository itemRepository;
	private final ItemFavoriteRepository itemFavoriteRepository;
	private final AccountRepository accountRepository;

	// 아이템 전체 불러오기 (즐겨찾기했는지 포함해서 보내기)
	@Transactional(readOnly = true)
	public List<ItemWithFavoriteResponse> findItemListWithFavorite(String username) {
		Account account = accountRepository.findByUsername(username)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계정입니다."));
		return itemRepository.findAll().stream()
				.map(item -> {
					boolean isFavorite = checkItemFavorite(account, item);
					return new ItemWithFavoriteResponse(item, isFavorite);
				})
				.collect(Collectors.toList());
	}

	private boolean checkItemFavorite(Account account, Item item){
		if (itemFavoriteRepository.findByAccountAndItem(account, item).isPresent()) {
			return true;
		} else {
			return false;
		}
	}

	// 아이템 검색하기
	@Transactional(readOnly = true)
	public List<ItemWithFavoriteResponse> findItemListByKeyword(String username, String keyword) {
		Account account = accountRepository.findByUsername(username)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계정입니다."));

		return itemRepository.findByNameContaining(keyword).stream()
			.map(item -> {
				boolean isFavorite = checkItemFavorite(account, item);
				return new ItemWithFavoriteResponse(item, isFavorite);
			})
			.collect(Collectors.toList());
	}

	// 즐겨찾기한 아이템 리스트 가져오기
	@Transactional
	public List<ItemResponse> findItemFavoriteListByAccount(String username){
		Account account = accountRepository.findByUsername(username)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계정입니다."));
		// 아이템 즐겨찾기 테이블에 계정에 맞는 아이템들 전체 불러와서 반환하기
		return itemRepository.findItemFavoriteByAccount(account.getId()).stream()
			.map(ItemResponse::new)
			.collect(Collectors.toList());
	}


	// 아이템 즐겨찾기 추가 및 해제
	@Transactional
	public void addItemFavorite(ItemFavoriteRequest requestDto){
		Account account = accountRepository.findByUsername(requestDto.getUsername())
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계정입니다."));
		Item item = itemRepository.findById(requestDto.getItemId())
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이템입니다."));
		// 아이템 즐겨찾기 여부 확인 후 삭제 및 저장
		if (itemFavoriteRepository.findByAccountAndItem(account, item).isPresent()){
			itemFavoriteRepository.delete(requestDto.toEntity(account, item));
		} else {
			itemFavoriteRepository.save(requestDto.toEntity(account, item));
		}
	}
}
