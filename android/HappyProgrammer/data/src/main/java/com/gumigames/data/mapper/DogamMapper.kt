package com.gumigames.data.mapper

import com.gumigames.data.BuildConfig
import com.gumigames.data.datasource.entity.ItemBookmarkEntity
import com.gumigames.data.model.response.dogam.ItemResponse
import com.gumigames.data.model.response.dogam.MonsterResponse
import com.gumigames.data.model.response.dogam.SkillResponse
import com.gumigames.domain.model.item.ItemDto
import com.gumigames.domain.model.item.MonsterDto
import com.gumigames.domain.model.item.SkillDto



/**
 * 서버에서 가져온 Response를 domain의 model로 변환
 */
fun ItemResponse.toDomain(): ItemDto{
    return ItemDto(
        id = id,
        name = name,
        description = description,
        imgPath = BuildConfig.BASE_URL + imgPath
    )
}
fun SkillResponse.toDomain(): SkillDto{
    return SkillDto(
        id = id,
        name = name,
        description = description,
        imgPath = BuildConfig.BASE_URL + imgPath
    )
}
fun MonsterResponse.toDomain(): MonsterDto{
    return MonsterDto(
        id = id,
        name = name,
        hp = hp,
        description = description,
        imgPath = BuildConfig.BASE_URL + imgPath
    )
}



