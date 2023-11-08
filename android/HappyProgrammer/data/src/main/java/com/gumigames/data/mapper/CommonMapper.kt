package com.gumigames.data.mapper

import com.gumigames.data.BuildConfig
import com.gumigames.data.model.response.common.ItemResponse
import com.gumigames.data.model.response.common.MonsterResponse
import com.gumigames.data.model.response.common.SkillResponse
import com.gumigames.domain.model.common.ItemDto
import com.gumigames.domain.model.common.MonsterDto
import com.gumigames.domain.model.common.SkillDto



/**
 * 서버에서 가져온 Response를 domain의 model로 변환
 */
fun ItemResponse.toDomain(): ItemDto{
    return ItemDto(
        id = id,
        name = name,
        description = description,
        imgPath = BuildConfig.BASE_URL + imgPath,
        isBookmarked = isBookmarked
    )
}
fun SkillResponse.toDomain(): SkillDto{
    return SkillDto(
        id = id,
        name = name,
        description = description,
        imgPath = BuildConfig.BASE_URL + imgPath,
        isBookmarked = isBookmarked
    )
}
fun MonsterResponse.toDomain(): MonsterDto{
    return MonsterDto(
        id = id,
        name = name,
        hp = hp,
        description = description,
        imgPath = BuildConfig.BASE_URL + imgPath,
        isBookmarked = isBookmarked
    )
}



