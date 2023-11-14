package com.ggteam.single.api.guide.dto.req;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class InventorySaveRequest {

    private Long characterId;
    private List<ItemSaveRequest> itemList;
}
