package com.ggteam.single.api.guide.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ggteam.single.api.guide.dto.MonsterDto;
import com.ggteam.single.api.guide.dto.SkillDto;
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

	@Operation(summary = "몬스터 정보 전체 조회")
	@GetMapping("/monsters")
	public ResponseEntity<List<MonsterDto>> monsterList(){
		List<MonsterDto> responseDto = monsterService.findAll();
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

	@Operation(summary = "스킬 정보 전체 조회")
	@GetMapping("/skills")
	public ResponseEntity<List<SkillDto>> skillList(){
		List<SkillDto> responseDto = skillService.findAll();
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}
}
