package com.ggteam.single.api.guide.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ggteam.single.api.guide.dto.req.ItemFavoriteRequest;
import com.ggteam.single.api.guide.dto.req.MonsterFavoriteRequest;
import com.ggteam.single.api.guide.dto.req.SkillFavoriteRequest;
import com.ggteam.single.api.guide.dto.res.ItemResponse;
import com.ggteam.single.api.guide.dto.res.ItemWithFavoriteResponse;
import com.ggteam.single.api.guide.dto.res.LevelResponse;
import com.ggteam.single.api.guide.dto.res.MonsterWithFavoriteResponse;
import com.ggteam.single.api.guide.dto.res.SkillResponse;
import com.ggteam.single.api.guide.dto.res.SkillWithFavoriteResponse;
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

	@Operation(summary = "레벨 정보 전체 조회")
	@GetMapping("/levels")
	public ResponseEntity<List<LevelResponse>> levelList() {
		List<LevelResponse> responseDto = levelService.findAll();
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

	// -------------아이템---------------
	@Operation(summary = "아이템 정보 전체 조회")
	@GetMapping("/items")
	public ResponseEntity<List<ItemWithFavoriteResponse>> itemList(Principal principal) {
		String username = principal.getName();
		List<ItemWithFavoriteResponse> response = itemService.findItemListWithFavorite(username);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "아이템 검색")
	@GetMapping("/items/search")
	public ResponseEntity<List<ItemWithFavoriteResponse>> itemSearchList(Principal principal, @RequestParam("keyword") String keyword) {
		String username = principal.getName();
		List<ItemWithFavoriteResponse> response = itemService.findItemListByKeyword(username, keyword);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "즐겨찾기한 아이템 조회하기")
	@GetMapping("/items/favorite")
	public ResponseEntity<?> itemFavoriteList(Principal principal){
		String username = principal.getName();
		List<ItemResponse> response = itemService.findItemFavoriteListByAccount(username);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "아이템 즐겨찾기하기", description = "이미 즐겨찾기 되어 있으면 취소된다.")
	@PostMapping("/items/favorite/{itemId}")
	public ResponseEntity<?> itemAddFavorite(Principal principal, @PathVariable Integer itemId){
		itemService.addItemFavorite(ItemFavoriteRequest.builder()
			.username(principal.getName())
			.itemId(itemId)
			.build());

		Map<String, String> response = new HashMap<>();
		response.put("result", "성공");

		return ResponseEntity.ok(response);
	}

	// -------------몬스터---------------

	@Operation(summary = "몬스터 정보 전체 조회")
	@GetMapping("/monsters")
	public ResponseEntity<List<MonsterWithFavoriteResponse>> monsterList(Principal principal) {
		String username = principal.getName();
		List<MonsterWithFavoriteResponse> response = monsterService.findMonsterListWithFavorite(username);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "몬스터 검색")
	@GetMapping("/monsters/search")
	public ResponseEntity<List<MonsterWithFavoriteResponse>> monsterSearchList(Principal principal, @RequestParam("keyword") String keyword) {
		String username = principal.getName();
		List<MonsterWithFavoriteResponse> response = monsterService.findMonsterListByKeyword(username, keyword);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "즐겨찾기한 몬스터 조회하기")
	@GetMapping("/monsters/favorite")
	public ResponseEntity<?> monsterFavoriteList(Principal principal){
		String username = principal.getName();
		List<ItemResponse> response = itemService.findItemFavoriteListByAccount(username);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "몬스터 즐겨찾기하기", description = "이미 즐겨찾기 되어 있으면 취소된다.")
	@PostMapping("/monsters/favorite/{monsterId}")
	public ResponseEntity<?> monsterAddFavorite(Principal principal, @PathVariable Integer monsterId){
		monsterService.addMonsterFavorite(MonsterFavoriteRequest.builder()
			.username(principal.getName())
			.monsterId(monsterId)
			.build());

		Map<String, String> response = new HashMap<>();
		response.put("result", "성공");

		return ResponseEntity.ok(response);
	}

	// -------------스킬---------------

	@Operation(summary = "스킬 정보 전체 조회")
	@GetMapping("/skills")
	public ResponseEntity<List<SkillWithFavoriteResponse>> skillList(Principal principal) {
		String username = principal.getName();
		List<SkillWithFavoriteResponse> response = skillService.findSkillListWithFavorite(username);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "스킬 검색")
	@GetMapping("/skills/search")
	public ResponseEntity<List<SkillWithFavoriteResponse>> skillSearchList(Principal principal, @RequestParam("keyword") String keyword) {
		String username = principal.getName();
		List<SkillWithFavoriteResponse> response = skillService.findSkillListByKeyword(username, keyword);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "즐겨찾기한 스킬 조회하기")
	@GetMapping("/skills/favorite")
	public ResponseEntity<?> skillFavoriteList(Principal principal){
		String username = principal.getName();
		List<SkillResponse> response = skillService.findSkillFavoriteListByAccount(username);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "스킬 즐겨찾기하기", description = "이미 즐겨찾기 되어 있으면 취소된다.")
	@PostMapping("/skills/favorite/{skillId}")
	public ResponseEntity<?> skillAddFavorite(Principal principal, @PathVariable Integer skillId){
		skillService.addSkillFavorite(SkillFavoriteRequest.builder()
			.username(principal.getName())
			.skillId(skillId)
			.build());

		Map<String, String> response = new HashMap<>();
		response.put("result", "성공");

		return ResponseEntity.ok(response);
	}
}
