package com.gumigames.data.mapper

import android.util.Log
import com.gumigames.data.BuildConfig
import com.gumigames.data.datasource.entity.ItemEntity
import com.gumigames.data.datasource.entity.MonsterEntity
import com.gumigames.data.datasource.entity.SkillEntity
import com.gumigames.data.model.response.common.ItemResponse
import com.gumigames.data.model.response.common.MonsterResponse
import com.gumigames.data.model.response.common.SkillResponse
import com.gumigames.domain.model.common.ItemDto
import com.gumigames.domain.model.common.MonsterDto
import com.gumigames.domain.model.common.SkillDto
import com.gumigames.domain.util.urlToBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val TAG = "차선호"
///////////////////////////////////////////// 아이템 ///////////////////////////////////////////////

suspend fun ItemResponse.toDomain(): ItemDto{
    return ItemDto(
        id = id,
        name = name,
        description = description,
        imageBitmap = withContext(Dispatchers.IO){ urlToBitmap(BuildConfig.BASE_URL + imgPath)!!},
        isBookmarked = isBookmarked
    )
}

fun ItemEntity.toDomain(): ItemDto{
    return ItemDto(
        id = id,
        name = name,
        description = description,
        imageBitmap = imageBitmap,
        isBookmarked = isBookmarked
    )
}

fun ItemDto.toData(): ItemEntity {
    return ItemEntity(
        id = id,
        name = name,
        description = description,
        imageBitmap = imageBitmap,
        isBookmarked = isBookmarked
    )
}


///////////////////////////////////////////// 스킬 ///////////////////////////////////////////////

suspend fun SkillResponse.toDomain(): SkillDto{
    return SkillDto(
        id = id,
        name = name,
        description = description,
        imageBitmap = withContext(Dispatchers.IO){ urlToBitmap(BuildConfig.BASE_URL + imgPath)!!},
        isBookmarked = isBookmarked
    )
}

fun SkillEntity.toDomain(): SkillDto{
    return SkillDto(
        id = id,
        name = name,
        description = description,
        imageBitmap = imageBitmap,
        isBookmarked = isBookmarked
    )
}

fun SkillDto.toData(): SkillEntity{
    return SkillEntity(
        id = id,
        name = name,
        description = description,
        imageBitmap = imageBitmap,
        isBookmarked = isBookmarked
    )
}

///////////////////////////////////////////// 몬스터 ///////////////////////////////////////////////

suspend fun MonsterResponse.toDomain(): MonsterDto{
    return MonsterDto(
        id = id,
        name = name,
        hp = hp,
        description = description,
        imageBitmap = withContext(Dispatchers.IO){ urlToBitmap(BuildConfig.BASE_URL + imgPath)!!},
        isBookmarked = isBookmarked
    )
}

fun MonsterEntity.toDomain(): MonsterDto{
    return MonsterDto(
        id = id,
        name = name,
        hp = hp,
        description = description,
        imageBitmap = imageBitmap,
        isBookmarked = isBookmarked
    )
}

fun MonsterDto.toData(): MonsterEntity{
    return MonsterEntity(
        id = id,
        name = name,
        hp = hp,
        description = description,
        imageBitmap = imageBitmap,
        isBookmarked = isBookmarked
    )
}


