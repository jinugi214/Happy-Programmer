package com.ggteam.single.api.guide.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name ="인벤토리", description = "내 인벤토리 관련 api")
@RequiredArgsConstructor
@RestController
@RequestMapping("/inventory")
public class InventoryController {
}
