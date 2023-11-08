package com.gumigames.data.mapper

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


///////////////////////////////////////////// 아이템 ///////////////////////////////////////////////

fun ItemResponse.toDomain(): ItemDto{
    return ItemDto(
        id = id,
        name = name,
        description = description,
        imgPath = BuildConfig.BASE_URL + imgPath,
        isBookmarked = isBookmarked
    )
}

fun ItemEntity.toDomain(): ItemDto{
    return ItemDto(
        id = id,
        name = name,
        description = description,
        imgPath = imgPath,
        isBookmarked = isBookmarked
    )
}

fun ItemDto.toData(): ItemEntity {
    return ItemEntity(
        id = id,
        name = name,
        description = description,
        imgPath = imgPath,
        isBookmarked = isBookmarked
    )
}


///////////////////////////////////////////// 스킬 ///////////////////////////////////////////////

fun SkillResponse.toDomain(): SkillDto{
    return SkillDto(
        id = id,
        name = name,
        description = description,
        imgPath = BuildConfig.BASE_URL + imgPath,
        isBookmarked = isBookmarked
    )
}

fun SkillEntity.toDomain(): SkillDto{
    return SkillDto(
        id = id,
        name = name,
        description = description,
        imgPath = imgPath,
        isBookmarked = isBookmarked
    )
}

fun SkillDto.toData(): SkillEntity{
    return SkillEntity(
        id = id,
        name = name,
        description = description,
        imgPath = imgPath,
        isBookmarked = isBookmarked
    )
}

///////////////////////////////////////////// 몬스터 ///////////////////////////////////////////////

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

fun MonsterEntity.toDomain(): MonsterDto{
    return MonsterDto(
        id = id,
        name = name,
        hp = hp,
        description = description,
        imgPath = imgPath,
        isBookmarked = isBookmarked
    )
}

fun MonsterDto.toData(): MonsterEntity{
    return MonsterEntity(
        id = id,
        name = name,
        hp = hp,
        description = description,
        imgPath = imgPath,
        isBookmarked = isBookmarked
    )
}


