package com.ggteam.single.api.guide.dto.res;

import com.ggteam.single.api.guide.entity.Inventory;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class InventoryItemResponse {
    private List<Inventory> inventoryList;
}