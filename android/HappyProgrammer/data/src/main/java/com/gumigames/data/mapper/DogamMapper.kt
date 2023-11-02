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
 * 로컬에서 가져온 Entity를 domain의 model로 변환
 */
//fun ItemBookmarkDao.toDomain(): ItemDto{
//    return ItemDto(
//
//    )
//}

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



/**
 * domain의 dto를 로컬 db의 ItemBookmarkEntity로
 */
fun ItemDto.toData(): ItemBookmarkEntity{
    return ItemBookmarkEntity(
        id = id,
        name = name,
        description = description,
        imagePath = imgPath
    )
}