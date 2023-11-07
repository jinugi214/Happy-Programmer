package com.ggteam.single.api.guide.dto.res;

import com.ggteam.single.api.guide.entity.Skill;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SkillWithFavoriteResponse {

	private Integer id;
	private String name;
	private String description;
	private String imgPath;
	private boolean isFavorite;

	public SkillWithFavoriteResponse(Skill entity, boolean isFavorite){
		this.id = entity.getId();
		this.name = entity.getName();
		this.description = entity.getDescription();
		this.imgPath = entity.getImgPath();
		this.isFavorite = isFavorite;
	}

}