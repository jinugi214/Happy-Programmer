package com.gumigames.data.mapper

import com.gumigames.data.datasource.dao.ItemBookmarkDao
import com.gumigames.data.datasource.entity.ItemBookmarkEntity
import com.gumigames.domain.model.item.ItemDto

/**
 * 로컬에서 가져온 Entity를 domain의 model로 변환
 */
fun ItemBookmarkEntity.toDomain(): ItemDto{
    return ItemDto(
        id = id,
        name = name,
        description = description,
        imgPath = imgPath
    )
}

/**
 * domain의 dto를 로컬 db의 ItemBookmarkEntity로
 */
fun ItemDto.toData(): ItemBookmarkEntity {
    return ItemBookmarkEntity(
        id = id,
        name = name,
        description = description,
        imgPath = imgPath
    )
}