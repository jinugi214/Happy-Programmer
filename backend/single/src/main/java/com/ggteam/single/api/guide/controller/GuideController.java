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

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/guide")
public class GuideController {

	private final MonsterService monsterService;
	private final SkillService skillService;

	@GetMapping("/monsters")
	public ResponseEntity<List<MonsterDto>> monsterList(){
		List<MonsterDto> responseDto = monsterService.findAll();
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

	@GetMapping("/skills")
	public ResponseEntity<List<SkillDto>> skillList(){
		List<SkillDto> responseDto = skillService.findAll();
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}
}
