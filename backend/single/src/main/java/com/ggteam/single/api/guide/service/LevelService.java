package com.ggteam.single.api.guide.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ggteam.single.api.guide.dto.LevelDto;
import com.ggteam.single.api.guide.dto.MonsterDto;
import com.ggteam.single.api.guide.repository.LevelRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LevelService {

	private LevelRepository levelRepository;

	// 레벨 전체 조회
	@Transactional(readOnly = true)
	public List<LevelDto> findAll() {
		return levelRepository.findAll().stream()
			.map(LevelDto::new)
			.collect(Collectors.toList());
	}
}
