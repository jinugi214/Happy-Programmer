package com.ggteam.single.api.guide.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ggteam.single.api.guide.dto.res.SkillResponse;
import com.ggteam.single.api.guide.repository.SkillRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SkillService {

	private final SkillRepository skillRepository;
	
	// 스킬 전체 불러오기
	@Transactional(readOnly = true)
	public List<SkillResponse> findAll(){
		return skillRepository.findAll().stream()
			.map(SkillResponse::new)
			.collect(Collectors.toList());
	}

	// 스킬 검색하기
	@Transactional(readOnly = true)
	public List<SkillResponse> findAllByName(String keyword) {
		return skillRepository.findByNameContaining(keyword).stream()
			.map(SkillResponse::new)
			.collect(Collectors.toList());
	}
}
