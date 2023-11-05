package com.ggteam.single.api.guide.controller;

import java.util.List;

import com.ggteam.single.api.guide.dto.res.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ggteam.single.api.account.entity.Account;
import com.ggteam.single.api.account.service.AccountService;
import com.ggteam.single.api.guide.dto.req.ItemFavoriteRequest;
import com.ggteam.single.api.guide.service.ItemService;
import com.ggteam.single.api.guide.service.LevelService;
import com.ggteam.single.api.guide.service.MonsterService;
import com.ggteam.single.api.guide.service.SkillService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name ="도감", description = "게임내 정보 조회")
@RequiredArgsConstructor
@RestController
@RequestMapping("/guide")
public class GuideController {

	private final MonsterService monsterService;
	private final SkillService skillService;
	private final LevelService levelService;
	private final ItemService itemService;

	@Operation(summary = "몬스터 정보 전체 조회")
	@GetMapping("/monsters")
	public ResponseEntity<List<MonsterResponse>> monsterList() {
		List<MonsterResponse> response = monsterService.findAll();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "몬스터 검색")
	@GetMapping("/monsters/search")
	public ResponseEntity<List<MonsterResponse>> monsterSearchList(@RequestParam("keyword") String keyword) {
		List<MonsterResponse> response = monsterService.findAllByName(keyword);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "스킬 정보 전체 조회")
	@GetMapping("/skills")
	public ResponseEntity<List<SkillResponse>> skillList() {
		List<SkillResponse> response = skillService.findAll();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "스킬 검색")
	@GetMapping("/skills/search")
	public ResponseEntity<List<SkillResponse>> skillSearchList(@RequestParam("keyword") String keyword) {
		List<SkillResponse> response = skillService.findAllByName(keyword);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "아이템 정보 전체 조회")
	@GetMapping("/items")
	public ResponseEntity<List<ItemWithFavoriteResponse>> itemList(@AuthenticationPrincipal Account account) {
		System.out.println(account);
		List<ItemWithFavoriteResponse> response = itemService.findItemListWithFavorite(account);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "아이템 검색")
	@GetMapping("/items/search")
	public ResponseEntity<List<ItemResponse>> itemSearchList(@RequestParam("keyword") String keyword) {
		List<ItemResponse> response = itemService.findItemListByKeyword(keyword);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "즐겨찾기한 아이템 조회하기")
	@GetMapping("/items/favorite")
	public ResponseEntity<?> itemFavoriteList(@AuthenticationPrincipal Account account){
		List<ItemResponse> response = itemService.findItemFavoriteListByAccount(account);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "아이템 즐겨찾기하기", description = "이미 즐겨찾기 되어 있으면 취소된다.")
	@PostMapping("/items/favorite/{itemId}")
	public ResponseEntity<?> itemAddFavorite(@AuthenticationPrincipal Account account, @PathVariable Integer itemId){
		itemService.addItemFavorite(ItemFavoriteRequest.builder()
			.account(account)
			.itemId(itemId)
			.build());
		return ResponseEntity.ok().build();
	}

	@Operation(summary = "레벨 정보 전체 조회")
	@GetMapping("/levels")
	public ResponseEntity<List<LevelResponse>> levelList() {
		List<LevelResponse> responseDto = levelService.findAll();
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}
}
