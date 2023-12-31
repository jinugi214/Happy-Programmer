package com.ggteam.single.api.guide.dto.res;

import com.ggteam.single.api.guide.entity.Item;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemResponse {

	private Integer id;
	private String name;
	private String description;
	private String imgPath;
	private boolean isOwned;

	public ItemResponse(Item entity, boolean isOwned){
		this.id = entity.getId();
		this.name = entity.getName();
		this.description = entity.getDescription();
		this.imgPath = entity.getImgPath();
		this.isOwned = isOwned;
	}
}
