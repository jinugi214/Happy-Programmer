package com.ggteam.single.api.guide.dto.req;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemSaveRequest {

    private Integer itemId;
    private int count;
    private boolean isEquipping;
}
