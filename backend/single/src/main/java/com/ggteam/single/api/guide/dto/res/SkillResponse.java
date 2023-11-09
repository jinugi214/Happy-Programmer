package com.ggteam.single.api.guide.dto.res;

import com.ggteam.single.api.guide.entity.Skill;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SkillResponse {

	private Integer id;
	private String name;
	private String description;
	private String imgPath;

	public SkillResponse(Skill entity){
		this.id = entity.getId();
		this.name = entity.getName();
		this.description = entity.getDescription();
		this.imgPath = entity.getImgPath();
	}
}
