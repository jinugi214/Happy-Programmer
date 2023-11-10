package com.ggteam.single.api.guide.dto.res;

import com.ggteam.single.api.guide.entity.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemWithFavoriteResponse {

    private Integer id;
    private String name;
    private String description;
    private String imgPath;
    private boolean isFavorite;
    private boolean isOwned;

    public ItemWithFavoriteResponse(Item entity, boolean isFavorite, boolean isOwned){
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.imgPath = entity.getImgPath();
        this.isFavorite = isFavorite;
        this.isOwned = isOwned;
    }

}