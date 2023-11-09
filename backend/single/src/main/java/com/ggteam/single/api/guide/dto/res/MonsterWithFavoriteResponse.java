package com.ggteam.single.api.guide.dto.res;

import com.ggteam.single.api.guide.entity.Monster;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MonsterWithFavoriteResponse {

	private Integer id;
	private String name;
	private String description;
	private int hp;
	private String imgPath;
	private boolean isFavorite;

	public MonsterWithFavoriteResponse(Monster entity, boolean isFavorite){
		this.id = entity.getId();
		this.name = entity.getName();
		this.description = entity.getDescription();
		this.hp = entity.getHp();
		this.imgPath = entity.getImgPath();
		this.isFavorite = isFavorite;
	}

}