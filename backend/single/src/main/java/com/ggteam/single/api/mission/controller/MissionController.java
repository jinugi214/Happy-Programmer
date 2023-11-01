package com.ggteam.single.api.mission.controller;


import com.ggteam.single.api.mission.service.MissionService;
import com.google.gson.JsonObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Tag(name ="미션", description = "게임 외부에서 수행해야하는 미션")
@RestController
@RequiredArgsConstructor
@RequestMapping("/game-feature")
@Slf4j
public class MissionController {

    @Operation(summary = "AI 객체 인식, quest-item에는 인식해야 하는 아이템을 영어로 입력 ex)keyboard")
    @GetMapping("/picture/{quest-item}")
    @CrossOrigin(origins = "*")
    public boolean detectObject(@PathVariable("quest-item") String questItem, @RequestPart(name = "file") MultipartFile image){



        String[] objects = MissionService.detectObject(image);

        for (int i = 0; i < objects.length; i++) {
            if (objects[i].equals(questItem)){
                return true;
            }
        }

        return false;
    }

}
