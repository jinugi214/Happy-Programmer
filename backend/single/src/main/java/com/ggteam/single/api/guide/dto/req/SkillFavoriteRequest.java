package com.ggteam.single.api.guide.dto.req;

import com.ggteam.single.api.account.entity.Account;
import com.ggteam.single.api.guide.entity.Skill;
import com.ggteam.single.api.guide.entity.SkillFavorite;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SkillFavoriteRequest{

	private String username;
	private Integer skillId;

	@Builder
	public SkillFavoriteRequest(String username, Integer skillId){
		this.username = username;
		this.skillId = skillId;
	}

	public SkillFavorite toEntity(Account account, Skill skill){
		return SkillFavorite.builder()
			.account(account)
			.skill(skill)
			.build();
	}
}
