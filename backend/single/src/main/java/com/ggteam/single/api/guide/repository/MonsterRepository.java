package com.ggteam.single.api.guide.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ggteam.single.api.guide.entity.Monster;

@Repository
public interface MonsterRepository extends JpaRepository<Monster, Long> {

	// 몬스터 이름 검색
	List<Monster> findByNameContaining(String keyword);
}
