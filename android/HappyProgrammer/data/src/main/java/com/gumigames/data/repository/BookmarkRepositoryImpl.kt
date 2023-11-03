package com.gumigames.data.repository

import com.gumigames.data.datasource.dao.ItemBookmarkDao
import com.gumigames.data.datasource.dao.MonsterBookmarkDao
import com.gumigames.data.datasource.dao.SkillBookmarkDao
import com.gumigames.data.mapper.toData
import com.gumigames.data.mapper.toDomain
import com.gumigames.domain.model.common.ItemDto
import com.gumigames.domain.model.common.MonsterDto
import com.gumigames.domain.model.common.SkillDto
import com.gumigames.domain.repository.BookmarkRepository

class BookmarkRepositoryImpl(
    private val itemBookmarkDao: ItemBookmarkDao,
    private val skillBookmarkDao: SkillBookmarkDao,
    private val monsterBookmarkDao: MonsterBookmarkDao
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
        return skillBookmarkDao.getAllBookmarkSkillsLocal().map { it.toDomain() }
    }
    /**
     * 즐겨찾기 스킬 로컬에 저장
     */
    override suspend fun addBookmarkSkillLocal(skillDto: SkillDto){
        skillBookmarkDao.addBookmarkSkillLocal(skillDto.toData())
    }
    /**
     * 로컬에 있는 즐겨찾기 몬스터 조회
     */
    override suspend fun getAllBookmarkMonstersLocal(): List<MonsterDto>{
        return monsterBookmarkDao.getAllBookmarkMonstersLocal().map { it.toDomain() }
    }
    /**
     * 즐겨찾기 몬스터 조회
     */
    override suspend fun addBookmarkMonsterLocal(monsterDto: MonsterDto){
        monsterBookmarkDao.addBookmarkMonsterLocal(monsterDto.toData())
    }


}