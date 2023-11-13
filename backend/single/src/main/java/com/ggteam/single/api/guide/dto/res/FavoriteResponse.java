package com.ggteam.single.api.guide.dto.res;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FavoriteResponse {

	private boolean favorite;

	public FavoriteResponse(boolean isFavorite){
		this.favorite = isFavorite;
	}
}
