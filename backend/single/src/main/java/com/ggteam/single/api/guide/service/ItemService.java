package com.ggteam.single.api.guide.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ggteam.single.api.account.entity.Account;
import com.ggteam.single.api.guide.dto.res.ItemResponse;
import com.ggteam.single.api.guide.dto.req.ItemFavoriteRequest;
import com.ggteam.single.api.guide.entity.Item;
import com.ggteam.single.api.guide.entity.ItemFavorite;
import com.ggteam.single.api.guide.repository.ItemFavoriteRepository;
import com.ggteam.single.api.guide.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ItemService {

	private final ItemRepository itemRepository;
	private final ItemFavoriteRepository itemFavoriteRepository;

	// 아이템 전체 불러오기 (즐겨찾기했는지 포함해서 보내기)
	@Transactional(readOnly = true)
	public List<ItemResponse> findItemList(){
		return itemRepository.findAll().stream()
			.map(ItemResponse::new)
			.collect(Collectors.toList());
	}

	// 아이템 검색하기
	@Transactional(readOnly = true)
	public List<ItemResponse> findItemListByKeyword(String keyword) {
		return itemRepository.findByNameContaining(keyword).stream()
			.map(ItemResponse::new)
			.collect(Collectors.toList());
	}

	// 즐겨찾기한 아이템 리스트 가져오기
	@Transactional
	public List<ItemResponse> findItemFavoriteListByAccount(Account account){
		// 아이템 즐겨찾기 테이블에 계정에 맞는 아이템들 전체 불러와서 반환하기
		return itemRepository.findItemFavoriteByAccount(account.getId()).stream()
			.map(ItemResponse::new)
			.collect(Collectors.toList());
	}


	// 아이템 즐겨찾기 추가 및 해제
	@Transactional
	public void addItemFavorite(ItemFavoriteRequest requestDto){
		Item item = itemRepository.findById(Long.valueOf(requestDto.getItemId()))
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이템입니다."));
		// 아이템 즐겨찾기 여부 확인 후 삭제 및 저장
		if (itemFavoriteRepository.findByAccountAndItem(requestDto.getAccount(), item).isPresent()){
			itemFavoriteRepository.delete(requestDto.toEntity(requestDto.getAccount(), item));
		} else {
			itemFavoriteRepository.save(requestDto.toEntity(requestDto.getAccount(), item));
		}
	}
}
