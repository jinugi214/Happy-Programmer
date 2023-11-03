package com.ggteam.single.api.guide.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ggteam.single.api.guide.dto.MonsterDto;
import com.ggteam.single.api.guide.repository.MonsterRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MonsterService {

	private final MonsterRepository monsterRepository;

	// 몬스터 전체 조회하기
	@Transactional(readOnly = true)
	public List<MonsterDto> findAll() {
		return monsterRepository.findAll().stream()
			.map(MonsterDto::new)
			.collect(Collectors.toList());
	}

	// 몬스터 검색하기
	@Transactional(readOnly = true)
	public List<MonsterDto> findAllByName(String keyword) {
		return monsterRepository.findByNameContaining(keyword).stream()
			.map(MonsterDto::new)
			.collect(Collectors.toList());
	}
}