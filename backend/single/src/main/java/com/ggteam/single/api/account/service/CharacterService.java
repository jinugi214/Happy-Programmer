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

    public ResponseEntity<?> myCharacter(String username) {
        Character character = characterRepository.findByAccount_Username(username).orElseThrow(null);

        CharacterDto characterDto = CharacterDto.builder()
                .name(character.getName())
                .gender(character.getGender())
                .level(character.getLevel())
                .exp(character.getExp())
                .savepoint(character.getSavepoint())
                .point(character.getPoint())
                .storyProgress(character.getStoryProgress())
                .imgPath(character.getImgPath())
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

    public ResponseEntity<?> checkNickname(String name) {
        if (characterRepository.findByName(name).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 존재하는 이름 입니다.");
        } else return ResponseEntity.ok("사용 가능한 이름 입니다.");
    }
}
