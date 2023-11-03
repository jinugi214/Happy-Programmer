package com.gumigames.data.repository

import com.gumigames.data.datasource.dao.ItemBookmarkDao
import com.gumigames.data.datasource.dao.SkillBookmarkDao
import com.gumigames.data.mapper.toData
import com.gumigames.data.mapper.toDomain
import com.gumigames.domain.model.item.ItemDto
import com.gumigames.domain.model.item.SkillDto
import com.gumigames.domain.repository.BookmarkRepository

class BookmarkRepositoryImpl(
    private val itemBookmarkDao: ItemBookmarkDao,
    private val bookmarkDao: SkillBookmarkDao
): BookmarkRepository {
    /**
     * 로컬에 있는 즐겨찾기 아이쳄 조회
     */
    override suspend fun getAllBookmarkItemsLocal(): List<ItemDto> {
        return itemBookmarkDao.getAllBookmarkItemsLocal().map { it.toDomain() }
    }

    /**
     * 즐겨찾기 아이템 로컬에 저장
     */
    override suspend fun addBookmarkItemLocal(itemDto: ItemDto) {
        itemBookmarkDao.addBookmarkItemLoocal(itemDto.toData())
    }

    /**
     * 로컬에 있는 즐겨찾기 스킬 조회
     */
    override suspend fun getAllBookmarkSkillsLocal(): List<SkillDto>{
        return bookmarkDao.getAllBookmarkSkillLocal().map { it.toDomain() }
    }
    /**
     * 즐겨찾기 스킬 로컬에 저장
     */
    override suspend fun addBookmarkSkillLocal(skillDto: SkillDto){
        bookmarkDao.addBookmarkSkillLocal(skillDto.toData())
    }


}