package com.ggteam.single.api.storyline.service;

import com.ggteam.single.api.account.entity.Character;
import com.ggteam.single.api.account.repository.CharacterRepository;
import com.ggteam.single.api.storyline.dto.request.QuestCompletionRequest;
import com.ggteam.single.api.storyline.dto.request.CreateQuestRequest;
import com.ggteam.single.api.storyline.dto.response.QuestResponse;
import com.ggteam.single.api.storyline.entity.Quest;
import com.ggteam.single.api.storyline.entity.QuestSuccess;
import com.ggteam.single.api.storyline.repository.QuestRepository;
import com.ggteam.single.api.storyline.repository.QuestSuccessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestService {

    private final QuestRepository questRepository;
    private final QuestSuccessRepository successRepository;
    private final CharacterRepository characterRepository;

    // 퀘스트 생성
    public ResponseEntity<?> makeQuest(CreateQuestRequest requestDto) {
        Quest quest = Quest.builder()
                .name(requestDto.getName())
                .description(requestDto.getDescription())
                .build();

        questRepository.save(quest);
        return ResponseEntity.ok("퀘스트가 생성되었습니다.");
    }

    // 퀘스트 전체 조회
    public ResponseEntity<?> allQuest() {
        List<Quest> allQuests = questRepository.findAll();

        List<QuestResponse> questResponses = allQuests.stream()
                .map(quest -> QuestResponse.builder()
                        .name(quest.getName())
                        .description(quest.getDescription())
                        .build())
                .collect(Collectors.toList());

        return ResponseEntity.ok(questResponses);
    }

    // 퀘스트 완료 여부 조회
    public ResponseEntity<?> isCleared(Long characterId, Integer questId) {
        Optional<QuestSuccess> questSuccess = successRepository.findByCharacterIdAndQuestId(characterId, questId);
        return ResponseEntity.ok(questSuccess.isPresent());
    }

    // 개별 퀘스트 완료
    public ResponseEntity<?> questDone(Long characterId, Integer questId) {
        Optional<Quest> optionalQuest = questRepository.findById(questId);
        Optional<Character> optionalCharacter = characterRepository.findById(characterId);

        if (optionalQuest.isPresent() && optionalCharacter.isPresent()) {
            Quest quest = optionalQuest.get();
            Character character = optionalCharacter.get();

            QuestSuccess questSuccess = QuestSuccess.builder()
                    .quest(quest)
                    .character(character)
                    .build();

            successRepository.save(questSuccess);
            return ResponseEntity.ok(quest.getName() + " 완료");
        } else {
            return ResponseEntity.badRequest().body("존재하지 않는 퀘스트이거나 캐릭터입니다.");
        }
    }

    // 여러 퀘스트 완료
    public ResponseEntity<?> completeQuests(QuestCompletionRequest request) {
        Optional<Character> optionalCharacter = characterRepository.findById(request.getCharacterId());

        if (!optionalCharacter.isPresent()) {
            return ResponseEntity.badRequest().body("존재하지 않는 캐릭터입니다 : " + request.getCharacterId());
        }

        Character character = optionalCharacter.get();

        for (Integer questId : request.getQuestIdList()) {
            Optional<Quest> optionalQuest = questRepository.findById(questId);

            if (!optionalQuest.isPresent()) {
                return ResponseEntity.badRequest().body("존재하지 않는 퀘스트입니다 : " + questId);
            }

            Quest quest = optionalQuest.get();

            QuestSuccess questSuccess = QuestSuccess.builder()
                    .quest(quest)
                    .character(character)
                    .build();

            successRepository.save(questSuccess);
        }

        return ResponseEntity.ok("퀘스트 완료");
    }

}
