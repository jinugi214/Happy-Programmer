package com.ggteam.single.api.guide.service;

import com.ggteam.single.api.account.entity.Character;
import com.ggteam.single.api.account.repository.CharacterRepository;
import com.ggteam.single.api.guide.dto.req.InventorySaveRequest;
import com.ggteam.single.api.guide.dto.req.ItemSaveRequest;
import com.ggteam.single.api.guide.entity.Inventory;
import com.ggteam.single.api.guide.entity.Item;
import com.ggteam.single.api.guide.repository.InventoryRepository;
import com.ggteam.single.api.guide.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final CharacterRepository characterRepository;
    private final ItemRepository itemRepository;


    // 인벤토리에 아이템 모두 저장하기
    @Transactional
    public ResponseEntity<String> saveItems(InventorySaveRequest inventorySaveRequest) {

        // 캐릭터 아이디로 찾은 모든 인벤토리
        List<Inventory> itemsPossessing = inventoryRepository.findAllByCharacterId(inventorySaveRequest.getCharacterId());
        // 요청으로 들어온 최신화된, 저장해야할 아이템 리스트
        List<ItemSaveRequest> itemsToSave = inventorySaveRequest.getItemList();

        for (ItemSaveRequest item : itemsToSave) {
            Inventory inventory = itemsPossessing.stream()
                    .filter(i -> i.getItem().getId().equals(item.getItemId()))
                    .findAny()
                    .orElse(null);

            if (inventory != null) {
                inventory.changeCount(item.getCount());
            } else {
                Character character = characterRepository.findById(inventorySaveRequest.getCharacterId())
                        .orElse(null);
                Item newItem = itemRepository.findById(item.getItemId())
                        .orElse(null);
                Inventory newInventory = Inventory.builder()
                        .character(character)
                        .item(newItem)
                        .count(item.getCount())
                        .isEquipping(item.isEquipping())
                        .build();
                itemsPossessing.add(newInventory);
            }
        }

        itemsPossessing.removeIf(inventory -> {
                    boolean isNotPresent = itemsToSave.stream().noneMatch(
                            i -> i.getItemId().equals(inventory.getItem().getId())
                    );

                    if (isNotPresent) {
                        inventoryRepository.delete(inventory);
                    }

//        inventoryRepository.saveAll(itemsPossessing);

            return isNotPresent;
        });
        return ResponseEntity.ok("아이템 저장 완료");
    }


    // 인벤토리 불러오기
    public ResponseEntity<?> myInventory(Long characterId) {
        List<Inventory> inventoryList = inventoryRepository.findAllByCharacterId(characterId);
        return ResponseEntity.ok(inventoryList);
    }
}
