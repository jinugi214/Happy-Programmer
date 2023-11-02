package com.gumigames.data.mapper

import com.gumigames.data.BuildConfig
import com.gumigames.data.datasource.entity.ItemBookmarkEntity
import com.gumigames.data.model.response.dogam.ItemResponse
import com.gumigames.data.model.response.dogam.MonsterResponse
import com.gumigames.domain.model.item.ItemDto
import com.gumigames.domain.model.item.MonsterDto

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
        imagePath = BuildConfig.BASE_URL + imgPath
    )
}
fun MonsterResponse.toDomain(): MonsterDto{
    return MonsterDto(
        id = id,
        name = name,
        hp = hp,
        description = description,
        imagePath = BuildConfig.BASE_URL + imgPath
    )
}



/**
 * domain의 dto를 로컬 db의 ItemBookmarkEntity로
 */
fun ItemDto.toData(): ItemBookmarkEntity{
    return ItemBookmarkEntity(
        id = -1,
        name = name,
        description = description,
        imagePath = imagePath
    )
}