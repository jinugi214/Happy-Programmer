package com.ggteam.single.api.mission.controller;


import com.ggteam.single.api.mission.service.MissionService;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequiredArgsConstructor
@RequestMapping("/game-feature")
@Slf4j
public class MissionController {

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
