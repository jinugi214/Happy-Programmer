package com.ggteam.single.api.guide.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ggteam.single.api.guide.entity.Monster;
import com.ggteam.single.api.guide.entity.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
	// 스킬 이름 검색
	List<Skill> findByNameContaining(String keyword);
}
