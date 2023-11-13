package com.ggteam.single.api.storyline.controller;

import com.ggteam.single.api.storyline.dto.request.CreateQuestRequest;
import com.ggteam.single.api.storyline.service.QuestService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "퀘스트", description = "퀘스트 관련 url")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/quest")
public class QuestController {

    private final QuestService questService;

    @PostMapping("/create")
    public ResponseEntity<?> createQuest(CreateQuestRequest questRequest) {
        return questService.makeQuest(questRequest);
    }

    @GetMapping("/all")
    public ResponseEntity<?> allQuests() {
        return questService.allQuest();
    }

    @GetMapping("/{characterId}/clear/{questId}")
    public ResponseEntity<?> isCleared(Long characterId, Integer questId) {
        return questService.isCleared(characterId, questId);
    }

}
