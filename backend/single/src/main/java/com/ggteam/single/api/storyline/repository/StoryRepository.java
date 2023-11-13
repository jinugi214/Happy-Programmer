package com.ggteam.single.api.storyline.repository;

import com.ggteam.single.api.storyline.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryRepository extends JpaRepository<Story, Integer> {
}
