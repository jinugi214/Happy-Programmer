package com.ggteam.single.api.inventory.dto;

import com.ggteam.single.api.guide.entity.Skill;
import com.ggteam.single.api.inventory.entity.Item;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemDto {

	private String name;
	private String description;
	private String imgPath;

	public ItemDto(Item entity){
		this.name = entity.getName();
		this.description = entity.getDescription();
		this.imgPath = entity.getImgPath();
	}
}
