package com.ggteam.single.api.guide.controller;

import com.ggteam.single.api.guide.dto.req.InventorySaveRequest;
import com.ggteam.single.api.guide.service.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name ="인벤토리", description = "내 인벤토리 관련 api")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;
    @PostMapping("/save")
    @Operation(summary = "아이템 저장", description = "아이템을 저장한다. 캐릭터아이디(characterId, Long), 아이템리스트" +
            "(itemList, List<Item>) 필요. 아이템은 아이템아이디(itemId, Integer), 개수(count, int), 장착여부(isEquipping, boolean) 필요")
    public ResponseEntity<?> saveInventory(@RequestBody InventorySaveRequest inventorySaveRequest) {
        return inventoryService.saveItems(inventorySaveRequest);
    }

}
