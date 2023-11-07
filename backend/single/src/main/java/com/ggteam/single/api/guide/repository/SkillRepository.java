package com.ggteam.single.api.guide.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ggteam.single.api.guide.entity.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer> {
	// 스킬 이름 검색
	List<Skill> findByNameContaining(String keyword);

	@Query("SELECT s FROM SkillFavorite sf JOIN Skill s ON sf.id = s.id WHERE sf.account.id = :accountId")
	List<Skill> findSkillFavoriteByAccount(@Param("accountId") Long accountId);

	Optional<Skill> findById(Integer skillId);
}
