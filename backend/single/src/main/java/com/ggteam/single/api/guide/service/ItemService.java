package com.ggteam.single.api.guide.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ggteam.single.api.guide.dto.res.ItemResponse;
import com.ggteam.single.api.guide.dto.req.ItemFavoriteRequest;
import com.ggteam.single.api.guide.entity.Item;
import com.ggteam.single.api.guide.repository.ItemFavoriteRepository;
import com.ggteam.single.api.guide.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ItemService {

	private final ItemRepository itemRepository;
	private final ItemFavoriteRepository itemFavoriteRepository;

	// 아이템 전체 불러오기
	@Transactional(readOnly = true)
	public List<ItemResponse> findAll(){
		return itemRepository.findAll().stream()
			.map(ItemResponse::new)
			.collect(Collectors.toList());
	}

	// 아이템 검색하기
	@Transactional(readOnly = true)
	public List<ItemResponse> findAllByName(String keyword) {
		return itemRepository.findByNameContaining(keyword).stream()
			.map(ItemResponse::new)
			.collect(Collectors.toList());
	}

	// 아이템 즐겨찾기 추가 및 해제
	@Transactional
	public void addItemFavorite(ItemFavoriteRequest requestDto){
		Item item = itemRepository.findById(Long.valueOf(requestDto.getItemId()))
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이템입니다."));
		itemFavoriteRepository.save(requestDto.toEntity(requestDto.getAccount(), item));
	}
}
