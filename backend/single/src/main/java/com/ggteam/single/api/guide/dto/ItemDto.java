package com.ggteam.single.api.guide.dto;

import com.ggteam.single.api.guide.entity.Item;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemDto {

	private Integer id;
	private String name;
	private String description;
	private String imgPath;

	public ItemDto(Item entity){
		this.id = entity.getId();
		this.name = entity.getName();
		this.description = entity.getDescription();
		this.imgPath = entity.getImgPath();
	}
}
