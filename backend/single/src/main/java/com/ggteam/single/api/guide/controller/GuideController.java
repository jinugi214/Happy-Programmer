package com.ggteam.single.api.guide.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ggteam.single.api.account.entity.Account;
import com.ggteam.single.api.account.service.AccountService;
import com.ggteam.single.api.guide.dto.res.ItemResponse;
import com.ggteam.single.api.guide.dto.req.ItemFavoriteRequest;
import com.ggteam.single.api.guide.dto.res.LevelResponse;
import com.ggteam.single.api.guide.dto.res.MonsterResponse;
import com.ggteam.single.api.guide.dto.res.SkillResponse;
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
	private final AccountService accountService;

	private Long accountId = 1L;

	// 컨트롤러에서 유저정보가져오기
	// id 값 추출 -> AccountService로 유저 객체 나온 것 service 파라미터로 넘겨주기
	@Operation(summary = "몬스터 정보 전체 조회")
	@GetMapping("/monsters")
	public ResponseEntity<List<MonsterResponse>> monsterList() {
		List<MonsterResponse> responseDto = monsterService.findAll();
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

	@Operation(summary = "몬스터 검색")
	@GetMapping("/monsters/search")
	public ResponseEntity<List<MonsterResponse>> monsterSearchList(@RequestParam("keyword") String keyword) {
		List<MonsterResponse> responseDto = monsterService.findAllByName(keyword);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

	@Operation(summary = "스킬 정보 전체 조회")
	@GetMapping("/skills")
	public ResponseEntity<List<SkillResponse>> skillList() {
		List<SkillResponse> responseDto = skillService.findAll();
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

	@Operation(summary = "스킬 검색")
	@GetMapping("/skills/search")
	public ResponseEntity<List<SkillResponse>> skillSearchList(@RequestParam("keyword") String keyword) {
		List<SkillResponse> responseDto = skillService.findAllByName(keyword);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

	@Operation(summary = "아이템 정보 전체 조회")
	@GetMapping("/items")
	public ResponseEntity<List<ItemResponse>> itemList() {
		List<ItemResponse> responseDto = itemService.findAll();
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

	@Operation(summary = "아이템 검색")
	@GetMapping("/items/search")
	public ResponseEntity<List<ItemResponse>> itemSearchList(@RequestParam("keyword") String keyword) {
		List<ItemResponse> responseDto = itemService.findAllByName(keyword);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

	@Operation(summary = "아이템 즐겨찾기")
	@PostMapping("/items/favorite")
	public ResponseEntity<?> itemAddFavorite(@PathVariable Integer itemId){
		Account account = accountService.findById(1L);
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
