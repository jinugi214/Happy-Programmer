package com.ggteam.single.api.save.dto;

import com.ggteam.single.api.guide.entity.Inventory;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoadResponse {

	private Character character;
	private Inventory inventory;

	public LoadResponse(Character character, Inventory inventory){
		this.character = character;
		this.inventory = inventory;
	}
}
