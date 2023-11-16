package com.ggteam.single.api.account.service;

import com.ggteam.single.api.account.dto.CharacterRequest;
import com.ggteam.single.api.account.dto.CharacterResponse;
import com.ggteam.single.api.account.entity.Character;
import com.ggteam.single.api.account.repository.AccountRepository;
import com.ggteam.single.api.account.repository.CharacterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;

@Service
@Transactional
@RequiredArgsConstructor
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final AccountRepository accountRepository;

    public ResponseEntity<?> myCharacter(String username) {
        Character character = characterRepository.findByAccount_Username(username).orElseThrow(null);

        CharacterResponse characterResponse = CharacterResponse.builder()
                .id(character.getId())
                .name(character.getName())
                .gender(character.getGender())
                .level(character.getLevel())
                .exp(character.getExp())
                .savepoint(character.getSavepoint())
                .point(character.getPoint())
                .storyProgress(character.getStoryProgress())
                .imgPath(character.getImgPath())
                .build();

        return ResponseEntity.ok(characterResponse);
    }

    public ResponseEntity<?> saveCharacter(CharacterRequest request, Principal principal) {

        String username = principal.getName();

        Character character = characterRepository.findByAccount_Username(username).orElse(null);
        if (character == null) {
            Character newCharacter = Character.builder()
                    .name(request.getName())
                    .gender(request.getGender())
                    .level(request.getLevel())
                    .exp(request.getExp())
                    .point(request.getPoint())
                    .savepoint(request.getSavepoint())
                    .imgPath(request.getImgPath())
                    .account(accountRepository.findByUsername(username).orElseThrow(NullPointerException::new))
                    .build();
            characterRepository.save(newCharacter);
        } else {
            character.updateCharacter(request.getExp(), request.getLevel(), request.getSavepoint(),
                    request.getPoint(), request.getStoryProgress(), request.getImgPath());
        }

        return ResponseEntity.ok("saved");
    }

    public ResponseEntity<?> checkNickname(String name) {
        if (characterRepository.findByName(name).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 존재하는 이름 입니다.");
        } else return ResponseEntity.ok("사용 가능한 이름 입니다.");
    }
}
