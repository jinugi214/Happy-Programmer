package com.ggteam.single.api.account.service;

import com.ggteam.single.api.account.dto.CharacterDto;
import com.ggteam.single.api.account.entity.Character;
import com.ggteam.single.api.account.repository.AccountRepository;
import com.ggteam.single.api.account.repository.CharacterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CharacterService {

    private final CharacterRepository characterRepository;

    public ResponseEntity<?> myCharacter(String accountId) {
        List<Character> characters = characterRepository.findAllByAccountIdByAccountId(accountId);
        Character myCharacter = characters.get(1);

        CharacterDto characterDto = CharacterDto.builder()
                .name(myCharacter.getName())
                .gender(myCharacter.getGender())
                .level(myCharacter.getLevel())
                .exp(myCharacter.getExp())
                .savepoint(myCharacter.getSavepoint())
                .point(myCharacter.getPoint())
                .storyProgress(myCharacter.getStoryProgress())
                .imgPath(myCharacter.getImgPath())
                .build();

        return ResponseEntity.ok(characterDto);
    }

    public ResponseEntity<?> saveCharater(@RequestBody CharacterDto characterDto) {
        if (characterRepository.count() > 2) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("더 이상 캐릭터를 생성할 수 없습니다.");
        }
        Character character = Character.builder()
                .name(characterDto.getName())
                .gender(characterDto.getGender())
                .level(characterDto.getLevel())
                .exp(characterDto.getExp())
                .point(characterDto.getPoint())
                .savepoint(characterDto.getSavepoint())
                .imgPath(characterDto.getImgPath())
                .build();
        characterRepository.save(character);

        return ResponseEntity.ok("saved");
    }
}
