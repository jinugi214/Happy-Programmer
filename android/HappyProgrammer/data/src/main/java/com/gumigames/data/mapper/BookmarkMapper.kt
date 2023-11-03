package com.gumigames.data.mapper

import com.gumigames.data.datasource.entity.ItemBookmarkEntity
import com.gumigames.data.datasource.entity.MonsterBookmarkEntity
import com.gumigames.data.datasource.entity.SkillBookmarkEntity
import com.gumigames.domain.model.common.ItemDto
import com.gumigames.domain.model.common.MonsterDto
import com.gumigames.domain.model.common.SkillDto

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
fun SkillBookmarkEntity.toDomain(): SkillDto{
    return SkillDto(
        id = id,
        name = name,
        description = description,
        imgPath = imgPath
    )
}
fun MonsterBookmarkEntity.toDomain(): MonsterDto{
    return MonsterDto(
        id = id,
        name = name,
        hp = hp,
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
fun SkillDto.toData(): SkillBookmarkEntity{
    return SkillBookmarkEntity(
        id = id,
        name = name,
        description = description,
        imgPath = imgPath
    )
}
fun MonsterDto.toData(): MonsterBookmarkEntity{
    return MonsterBookmarkEntity(
        id = id,
        name = name,
        hp = hp,
        description = description,
        imgPath = imgPath
    )
}