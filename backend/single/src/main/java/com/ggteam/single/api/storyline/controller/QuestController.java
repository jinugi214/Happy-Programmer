package com.ggteam.single.api.storyline.controller;

import com.ggteam.single.api.storyline.dto.request.CreateQuestRequest;
import com.ggteam.single.api.storyline.dto.request.QuestCompletionRequest;
import com.ggteam.single.api.storyline.service.QuestService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "퀘스트", description = "퀘스트 관련 url")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/quest")
public class QuestController {

    private final QuestService questService;

    @PostMapping("/create")
    public ResponseEntity<?> createQuest(@RequestBody CreateQuestRequest questRequest) {
        return questService.makeQuest(questRequest);
    }

    @GetMapping("/all")
    public ResponseEntity<?> allQuests() {
        return questService.allQuest();
    }

    @GetMapping("/{characterId}/is-clear/{questId}")
    public ResponseEntity<?> isCleared(@PathVariable Long characterId, @PathVariable Integer questId) {
        return questService.isCleared(characterId, questId);
    }

    @PostMapping("/{characterId}/done/{questId}")
    public ResponseEntity<?> questDone(@PathVariable Long characterId, @PathVariable Integer questId) {
        return questService.questDone(characterId, questId);
    }

    @PostMapping("/sync")
    public ResponseEntity<?> finishedQuest(@RequestBody QuestCompletionRequest request) {
        return questService.completeQuests(request);
    }

}
