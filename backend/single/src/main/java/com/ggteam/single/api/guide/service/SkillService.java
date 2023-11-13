package com.ggteam.single.api.guide.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ggteam.single.api.account.entity.Account;
import com.ggteam.single.api.account.repository.AccountRepository;
import com.ggteam.single.api.guide.dto.req.SkillFavoriteRequest;
import com.ggteam.single.api.guide.dto.res.FavoriteResponse;
import com.ggteam.single.api.guide.dto.res.SkillResponse;
import com.ggteam.single.api.guide.dto.res.SkillWithFavoriteResponse;
import com.ggteam.single.api.guide.entity.Skill;
import com.ggteam.single.api.guide.entity.SkillFavorite;
import com.ggteam.single.api.guide.repository.SkillFavoriteRepository;
import com.ggteam.single.api.guide.repository.SkillRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SkillService {

	private final SkillRepository skillRepository;
	private final SkillFavoriteRepository skillFavoriteRepository;
	private final AccountRepository accountRepository;

	// 스킬 전체 불러오기 (즐겨찾기했는지 포함해서 보내기)
	@Transactional(readOnly = true)
	public List<SkillWithFavoriteResponse> findSkillListWithFavorite(String username) {
		Account account = accountRepository.findByUsername(username)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계정입니다."));
		return skillRepository.findAll().stream()
			.map(Skill -> {
				boolean isFavorite = checkSkillFavorite(account, Skill);
				return new SkillWithFavoriteResponse(Skill, isFavorite);
			})
			.collect(Collectors.toList());
	}

	private boolean checkSkillFavorite(Account account, Skill skill){
		if (skillFavoriteRepository.findByAccountAndSkill(account, skill).isPresent()) {
			return true;
		} else {
			return false;
		}
	}

	// 스킬 검색하기
	@Transactional(readOnly = true)
	public List<SkillWithFavoriteResponse> findSkillListByKeyword(String username, String keyword) {
		Account account = accountRepository.findByUsername(username)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계정입니다."));

		return skillRepository.findByNameContaining(keyword).stream()
			.map(Skill -> {
				boolean isFavorite = checkSkillFavorite(account, Skill);
				return new SkillWithFavoriteResponse(Skill, isFavorite);
			})
			.collect(Collectors.toList());
	}

	// 즐겨찾기한 스킬 리스트 가져오기
	@Transactional(readOnly = true)
	public List<SkillResponse> findSkillFavoriteListByAccount(String username){
		Account account = accountRepository.findByUsername(username)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계정입니다."));
		// 아이템 즐겨찾기 테이블에 계정에 맞는 아이템들 전체 불러와서 반환하기
		return skillRepository.findSkillFavoriteByAccount(account.getId()).stream()
			.map(SkillResponse::new)
			.collect(Collectors.toList());
	}


	// 스킬 즐겨찾기 추가 및 해제
	@Transactional
	public FavoriteResponse addSkillFavorite(SkillFavoriteRequest requestDto){
		Account account = accountRepository.findByUsername(requestDto.getUsername())
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계정입니다."));
		Skill skill = skillRepository.findById(requestDto.getSkillId())
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 스킬입니다."));
		// 몬스터 즐겨찾기 여부 확인 후 삭제 및 저장
		Optional<SkillFavorite> skillFavorite = skillFavoriteRepository.findByAccountAndSkill(account, skill);
		if (skillFavorite.isPresent()){
			skillFavoriteRepository.delete(skillFavorite.get());
			return new FavoriteResponse(false);
		} else {
			skillFavoriteRepository.save(requestDto.toEntity(account, skill));
			return new FavoriteResponse(true);
		}
	}
}
