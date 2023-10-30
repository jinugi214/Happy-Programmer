package com.ggteam.single.api.inventory.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ggteam.single.api.guide.dto.MonsterDto;
import com.ggteam.single.api.inventory.dto.ItemDto;
import com.ggteam.single.api.inventory.service.ItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name ="인벤토리", description = "캐릭터 인벤토리 조회")
@RequiredArgsConstructor
@RestController
@RequestMapping("/inventory")
public class InventoryController {

	private final ItemService itemService;

	@Operation(summary = "아이템 정보 전체 조회")
	@GetMapping("/items")
	public ResponseEntity<List<ItemDto>> itemList(){
		List<ItemDto> responseDto = itemService.findAll();
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}
}
