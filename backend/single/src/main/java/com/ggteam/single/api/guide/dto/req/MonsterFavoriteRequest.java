package com.ggteam.single.api.guide.dto.req;

import com.ggteam.single.api.account.entity.Account;
import com.ggteam.single.api.guide.entity.Monster;
import com.ggteam.single.api.guide.entity.MonsterFavorite;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MonsterFavoriteRequest {

	private String username;
	private Integer monsterId;

	@Builder
	public MonsterFavoriteRequest(String username, Integer monsterId){
		this.username = username;
		this.monsterId = monsterId;
	}

	public MonsterFavorite toEntity(Account account, Monster monster){
		return MonsterFavorite.builder()
			.account(account)
			.monster(monster)
			.build();
	}
}