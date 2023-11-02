package com.ggteam.single.api.guide.dto.res;

import com.ggteam.single.api.guide.entity.Monster;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MonsterResponse {

	private Integer id;
	private String name;
	private String description;
	private int hp;
	private String imgPath;

	public MonsterResponse(Monster entity){
		this.id = entity.getId();
		this.name = entity.getName();
		this.description = entity.getDescription();
		this.hp = entity.getHp();
		this.imgPath = entity.getImgPath();
	}
}
