package com.gumigames.data.mapper

import com.gumigames.data.BuildConfig
import com.gumigames.data.datasource.dao.ItemBookmarkDao
import com.gumigames.data.model.response.ItemResponse
import com.gumigames.domain.model.item.ItemDto

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
        id = -1,
        name = name,
        description = description,
        imagePath = BuildConfig.BASE_URL + imgPath
    )
}