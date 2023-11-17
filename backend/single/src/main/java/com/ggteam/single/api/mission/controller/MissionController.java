package com.ggteam.single.api.mission.controller;


import com.ggteam.single.api.mission.service.MissionService;
import com.google.gson.JsonObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Tag(name ="미션", description = "게임 외부에서 수행해야하는 미션")
@RestController
@RequiredArgsConstructor
@RequestMapping("/game-feature")
@Slf4j
public class MissionController {

    @Operation(summary = "AI 객체 인식")
    @PostMapping(value = "/picture", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @CrossOrigin(origins = "*")
    public Map<String, String> detectObject(@RequestPart(name = "file") MultipartFile image) {

        String[] objects = MissionService.detectObject(image);
        Map<String, String> result = new HashMap<>();

        boolean flag = false;

        for (int i = 0; i < objects.length; i++) {
            if (objects[i].equals("keyboard")){
                result.put("result", "성공");
                flag = true;
                break;
            }
            if(objects[i].equals("오류")){
                result.put("result", "다시 찍어주세요!");
                flag = true;
                break;
            }
        }

        if (!flag)
            result.put("result", "실패");



        return result;
    }

}
