package com.ggteam.single.api.guide.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ggteam.single.api.guide.dto.ItemDto;
import com.ggteam.single.api.guide.dto.SkillDto;
import com.ggteam.single.api.guide.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ItemService {

	private final ItemRepository itemRepository;

	// 아이템 전체 불러오기
	@Transactional(readOnly = true)
	public List<ItemDto> findAll(){
		return itemRepository.findAll().stream()
			.map(ItemDto::new)
			.collect(Collectors.toList());
	}

	// 아이템 검색하기
	@Transactional(readOnly = true)
	public List<ItemDto> findAllByName(String keyword) {
		return itemRepository.findByNameContaining(keyword).stream()
			.map(ItemDto::new)
			.collect(Collectors.toList());
	}

	// 아이템 즐겨찾기 추가 및 해제
}
