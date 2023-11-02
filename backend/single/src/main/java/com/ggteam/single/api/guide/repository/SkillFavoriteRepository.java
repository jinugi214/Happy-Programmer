package com.ggteam.single.api.guide.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ggteam.single.api.guide.entity.ItemFavorite;
import com.ggteam.single.api.guide.entity.SkillFavorite;

public interface SkillFavoriteRepository extends JpaRepository<SkillFavorite, Integer> {
}
