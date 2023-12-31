package com.ggteam.single.api.save.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ggteam.single.api.save.dto.LoadResponse;
import com.ggteam.single.api.save.dto.SaveRequest;
import com.ggteam.single.api.save.service.SaveService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name ="세이브", description = "게임 저장 및 불러오기")
@RequiredArgsConstructor
@RestController
@RequestMapping("/save")
public class SaveController {

	private final SaveService saveService;

	@Operation(summary = "게임 불러오기")
	@GetMapping("")
	public ResponseEntity<LoadResponse> loadGame(Principal principal) {
		String username = principal.getName();
		LoadResponse responseDto = saveService.loadGame(username);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

	@Operation(summary = "게임 저장")
	@PostMapping ("")
	public ResponseEntity<?> saveGame(Principal principal, @RequestBody SaveRequest saveRequest) {
		String username = principal.getName();
		saveService.saveGame(username, saveRequest);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}


}
