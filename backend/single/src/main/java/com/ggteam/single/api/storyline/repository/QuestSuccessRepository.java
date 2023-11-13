package com.ggteam.single.api.storyline.repository;

import com.ggteam.single.api.storyline.entity.QuestSuccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestSuccessRepository extends JpaRepository<QuestSuccess, Integer> {

    Optional<QuestSuccess> findByCharacterIdAndQuestId(Long characterId, Integer questId);

}
