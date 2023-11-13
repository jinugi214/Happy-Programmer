package com.ggteam.single.api.guide.dto.req;

import com.ggteam.single.api.account.entity.Account;
import com.ggteam.single.api.account.entity.Character;
import com.ggteam.single.api.guide.entity.Inventory;
import com.ggteam.single.api.guide.entity.Item;
import com.ggteam.single.api.guide.entity.ItemFavorite;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InventoryRequest {

	private int count;
	private boolean isEquipping;
	private Long characterId;
	private Integer itemId;


	@Builder
	public InventoryRequest(int count, boolean isEquipping, Long characterId, Integer itemId){
		this.count = count;
		this. isEquipping = isEquipping;
		this.characterId = characterId;
		this.itemId = itemId;
	}

	public Inventory toEntity(Character character, Item item){
		return Inventory.builder()
			.count(count)
			.isEquipping(isEquipping)
			.character(character)
			.item(item)
			.build();
	}
}
