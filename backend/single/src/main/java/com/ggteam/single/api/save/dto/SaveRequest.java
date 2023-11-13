package com.ggteam.single.api.save.dto;

import java.util.List;

import com.ggteam.single.api.account.entity.Character;
import com.ggteam.single.api.guide.dto.req.InventoryRequest;
import com.ggteam.single.api.guide.entity.Inventory;
import com.ggteam.single.api.guide.entity.Item;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SaveRequest {

	private int exp;
	private int level;
	private String savepoint;
	private int point;
	private int storyProgress;
	private String imgPath;
	private List<Inventory> inventoryList;

	@Builder
	public SaveRequest(int exp, int level, String savepoint, int point, int storyProgress, String imgPath, List<Inventory> inventoryList){
		this.exp = exp;
		this.level = level;
		this.savepoint = savepoint;
		this.point = point;
		this.storyProgress = storyProgress;
		this.imgPath = imgPath;
		this.inventoryList = inventoryList;
	}

}