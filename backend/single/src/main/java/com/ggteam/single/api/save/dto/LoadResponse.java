package com.ggteam.single.api.save.dto;

import java.util.List;

import com.ggteam.single.api.account.entity.Character;
import com.ggteam.single.api.guide.entity.Inventory;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoadResponse {

	private Character character;
	private List<Inventory> inventoryList;

	public LoadResponse(Character character, List<Inventory> inventoryList){
		this.character = character;
		this.inventoryList = inventoryList;
	}
}
