package com.ggteam.single.api.guide.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ggteam.single.api.account.entity.Account;
import com.ggteam.single.api.account.repository.AccountRepository;
import com.ggteam.single.api.guide.dto.req.MonsterFavoriteRequest;
import com.ggteam.single.api.guide.dto.res.FavoriteResponse;
import com.ggteam.single.api.guide.dto.res.MonsterResponse;
import com.ggteam.single.api.guide.dto.res.MonsterWithFavoriteResponse;
import com.ggteam.single.api.guide.entity.Monster;
import com.ggteam.single.api.guide.entity.MonsterFavorite;
import com.ggteam.single.api.guide.entity.SkillFavorite;
import com.ggteam.single.api.guide.repository.MonsterFavoriteRepository;
import com.ggteam.single.api.guide.repository.MonsterRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MonsterService {

	private final MonsterRepository monsterRepository;
	private final MonsterFavoriteRepository monsterFavoriteRepository;
	private final AccountRepository accountRepository;

	// 몬스터 전체 불러오기 (즐겨찾기했는지 포함해서 보내기)
	@Transactional(readOnly = true)
	public List<MonsterWithFavoriteResponse> findMonsterListWithFavorite(String username) {
		Account account = accountRepository.findByUsername(username)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계정입니다."));
		return monsterRepository.findAll().stream()
			.map(Monster -> {
				boolean isFavorite = checkMonsterFavorite(account, Monster);
				return new MonsterWithFavoriteResponse(Monster, isFavorite);
			})
			.collect(Collectors.toList());
	}

	private boolean checkMonsterFavorite(Account account, Monster Monster){
		if (monsterFavoriteRepository.findByAccountAndMonster(account, Monster).isPresent()) {
			return true;
		} else {
			return false;
		}
	}

	// 몬스터 검색하기
	@Transactional(readOnly = true)
	public List<MonsterWithFavoriteResponse> findMonsterListByKeyword(String username, String keyword) {
		Account account = accountRepository.findByUsername(username)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계정입니다."));

		return monsterRepository.findByNameContaining(keyword).stream()
			.map(Monster -> {
				boolean isFavorite = checkMonsterFavorite(account, Monster);
				return new MonsterWithFavoriteResponse(Monster, isFavorite);
			})
			.collect(Collectors.toList());
	}

	// 즐겨찾기한 몬스터 리스트 가져오기
	@Transactional(readOnly = true)
	public List<MonsterResponse> findMonsterFavoriteListByAccount(String username){
		Account account = accountRepository.findByUsername(username)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계정입니다."));
		// 아이템 즐겨찾기 테이블에 계정에 맞는 아이템들 전체 불러와서 반환하기
		return monsterRepository.findMonsterFavoriteByAccount(account.getId()).stream()
			.map(MonsterResponse::new)
			.collect(Collectors.toList());
	}


	// 몬스터 즐겨찾기 추가 및 해제
	@Transactional
	public FavoriteResponse addMonsterFavorite(MonsterFavoriteRequest requestDto){
		Account account = accountRepository.findByUsername(requestDto.getUsername())
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계정입니다."));
		Monster monster = monsterRepository.findById(requestDto.getMonsterId())
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 몬스터입니다."));
		// 몬스터 즐겨찾기 여부 확인 후 삭제 및 저장
		Optional<MonsterFavorite> monsterFavorite = monsterFavoriteRepository.findByAccountAndMonster(account, monster);
		if (monsterFavorite.isPresent()){
			monsterFavoriteRepository.delete(monsterFavorite.get());
			return new FavoriteResponse(false);
		} else {
			monsterFavoriteRepository.save(requestDto.toEntity(account, monster));
			return new FavoriteResponse(true);
		}
	}
}