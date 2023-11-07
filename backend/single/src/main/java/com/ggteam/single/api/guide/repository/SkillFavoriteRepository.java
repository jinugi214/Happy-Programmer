package com.ggteam.single.api.guide.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ggteam.single.api.account.entity.Account;
import com.ggteam.single.api.guide.entity.Skill;
import com.ggteam.single.api.guide.entity.SkillFavorite;

public interface SkillFavoriteRepository extends JpaRepository<SkillFavorite, Integer> {

	Optional<SkillFavorite> findByAccountAndSkill(Account account, Skill skill);
}
