package com.ggteam.single.api.account.controller;

import com.ggteam.single.api.account.dto.CharacterDto;
import com.ggteam.single.api.account.service.CharacterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Tag(name = "캐릭터 정보", description = "캐릭터 관련")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/character")
public class CharacterController {

    private final CharacterService characterService;

    // 내 캐릭터 정보 가져오기
    @GetMapping("/")
    @Operation(summary = "캐릭터 정보", description = "내 캐릭터 정보를 가져온다. 로그인 상태(토큰필요)에서 요청 시 정보를 줌")
    public ResponseEntity<?> myCharacter(Principal principal) {
        return characterService.myCharacter(principal.getName());
    }

    // 캐릭터 정보 저장
    @PostMapping("/save")
    @Operation(summary = "캐릭터 정보 저장", description = "JSON으로 이름(name, String), 성별(gender, char), 경험치(exp, int)," +
            " 레벨(level, int), 포인트(point, int), 저장위치(savepoint, int), 캐릭터 이미지(imgPath, String) 필요")
    public ResponseEntity<?> saveCharacter(@RequestBody CharacterDto characterDto, UserDetails userDetails) {
        return characterService.saveCharater(characterDto, userDetails);
    }

    @PostMapping("/check/nickname/{name}")
    @Operation(summary = "캐릭터 이름 중복체크", description = "이름(name, String)을 입력 시, 중복이라면 400 BAD_REQUSET, " +
            "아니라면 200 OK 반환")
    public ResponseEntity<?> checkNickname(@PathVariable String name) {
        return characterService.checkNickname(name);
    }
}
