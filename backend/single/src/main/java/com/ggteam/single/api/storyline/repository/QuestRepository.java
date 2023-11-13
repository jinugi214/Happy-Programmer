package com.ggteam.single.api.storyline.repository;

import com.ggteam.single.api.storyline.entity.Quest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestRepository extends JpaRepository<Quest, Integer> {
}
