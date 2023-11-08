package com.gumigames.data.mapper

import com.gumigames.data.datasource.entity.ItemEntity
import com.gumigames.data.datasource.entity.MonsterEntity
import com.gumigames.data.datasource.entity.SkillEntity
import com.gumigames.domain.model.common.ItemDto
import com.gumigames.domain.model.common.MonsterDto
import com.gumigames.domain.model.common.SkillDto

/**
 * 로컬에서 가져온 Entity를 domain의 model로 변환
 */
fun ItemEntity.toDomain(): ItemDto{
    return ItemDto(
        id = id,
        name = name,
        description = description,
        imgPath = imgPath,
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

/**
 * domain의 dto를 로컬 db의 ItemBookmarkEntity로
 */
fun ItemDto.toData(): ItemEntity {
    return ItemEntity(
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