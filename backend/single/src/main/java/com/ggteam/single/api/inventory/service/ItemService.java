package com.ggteam.single.api.inventory.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ggteam.single.api.inventory.dto.ItemDto;
import com.ggteam.single.api.inventory.repository.ItemRepository;

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
}
