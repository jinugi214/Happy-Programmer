package com.ggteam.single.api.guide.dto.req;

import com.ggteam.single.api.account.entity.Account;
import com.ggteam.single.api.guide.entity.Item;
import com.ggteam.single.api.guide.entity.ItemFavorite;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemFavoriteRequest {

	private Account account;
	private Integer itemId;

	@Builder
	public ItemFavoriteRequest(Account account, Integer itemId){
		this.account = account;
		this.itemId = itemId;
	}

	public ItemFavorite toEntity(Account account, Item item){
		return ItemFavorite.builder()
			.account(account)
			.item(item)
			.build();
	}
}
