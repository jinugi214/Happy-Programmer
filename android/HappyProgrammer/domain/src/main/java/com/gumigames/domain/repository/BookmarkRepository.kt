package com.gumigames.domain.repository

import com.gumigames.domain.model.item.ItemDto
import com.gumigames.domain.model.item.SkillDto

interface BookmarkRepository {
    /**
     * 로컬에 있는 즐겨찾기 아이템 조회
     */
    suspend fun getAllBookmarkItemsLocal(): List<ItemDto>
    /**
     * 즐겨찾기 아이템 로컬에 저장
     */
    suspend fun addBookmarkItemLocal(itemDto: ItemDto)
    /**
     * 로컬에 있는 즐겨찾기 스킬 조회
     */
    suspend fun getAllBookmarkSkillsLocal(): List<SkillDto>
    /**
     * 즐겨찾기 스킬 로컬에 저장
     */
    suspend fun addBookmarkSkillLocal(skillDto: SkillDto)
}