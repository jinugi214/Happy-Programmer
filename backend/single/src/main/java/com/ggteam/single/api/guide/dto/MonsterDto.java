package com.ggteam.single.api.guide.dto;

import com.ggteam.single.api.guide.entity.Monster;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MonsterDto {

	private String name;

	private String description;

	private int hp;

	private String imgPath;

	public MonsterDto(Monster entity){
		this.name = entity.getName();
		this.description = entity.getDescription();
		this.hp = entity.getHp();
		this.imgPath = entity.getImgPath();
	}
}
