package com.ggteam.single.api.guide.dto;

import com.ggteam.single.api.guide.entity.Skill;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SkillDto {

	private String name;

	private String description;

	private String imgPath;

	public SkillDto(Skill entity){
		this.name = entity.getName();
		this.description = entity.getDescription();
		this.imgPath = entity.getImgPath();
	}
}
