package com.gumigames.data.repository

import com.gumigames.data.datasource.dao.ItemDao
import com.gumigames.data.datasource.dao.MonsterDao
import com.gumigames.data.datasource.dao.SkillDao
import com.gumigames.data.mapper.toDomain
import com.gumigames.data.service.ItemService
import com.gumigames.data.service.MonsterService
import com.gumigames.data.service.SkillService
import com.gumigames.data.util.handleApi
import com.gumigames.domain.model.common.ItemDto
import com.gumigames.domain.model.common.MonsterDto
import com.gumigames.domain.model.common.SkillDto
import com.gumigames.domain.repository.DogamRepository

class DogamRepositoryImpl(
    private val itemService: ItemService,
    private val skillService: SkillService,
    private val monsterService: MonsterService,
    private val itemDao: ItemDao,
    private val skillDao: SkillDao,
    private val monsterDao: MonsterDao
): DogamRepository {
    /**
     * 아이템 전체 조회
     */
    override suspend fun getAllItems(): List<ItemDto> {
        return handleApi { itemService.getAllItems() }.map { it.toDomain() }
    }

    /**
     * 아이템 검색
     */
    override suspend fun getSearchItems(keyword: String): List<ItemDto> {
        return handleApi { itemService.getSearchItems(keyword) }.map { it.toDomain() }
    }

    /**
     * 스킬 전체 조회
     */
    override suspend fun getAllSkills(): List<SkillDto> {
        return handleApi { skillService.getAllSkills() }.map { it.toDomain() }
    }

    /**
     * 스킬 검색
     */
    override suspend fun getSearchSkills(keyword: String): List<SkillDto> {
        return handleApi { skillService.getSearchSkills(keyword) }.map { it.toDomain() }
    }

    /**
     * 몬스터 전체 조회
     */
    override suspend fun getAllMonsters(): List<MonsterDto> {
        return handleApi { monsterService.getAllMonsters() }.map { it.toDomain() }
    }

    /**
     * 몬스터 검색
     */
    override suspend fun getSearchMonsters(keyword: String): List<MonsterDto> {
        return handleApi { monsterService.getSearchMonsters(keyword) }.map { it.toDomain() }
    }

    //////////////////////////////// 로컬 /////////////////////////////////////////////////////

    /**
     * 로컬에 있는 즐겨찾기 아이쳄 조회
     */
    override suspend fun getAllBookmarkItemsLocal(): List<ItemDto> {
        return itemDao.getAllBookmarkItemsLocal().map { it.toDomain() }
    }

    /**
     * 즐겨찾기 아이템 로컬에 저장
     */
    override suspend fun addBookmarkItemLocal(itemId: Int) {
        itemDao.addBookmarkItemLocal(itemId)
    }

    /**
     * 로컬에 있는 즐겨찾기 스킬 조회
     */
    override suspend fun getAllBookmarkSkillsLocal(): List<SkillDto>{
        return skillDao.getAllBookmarkSkillsLocal().map { it.toDomain() }
    }
    /**
     * 즐겨찾기 스킬 로컬에 저장
     */
    override suspend fun addBookmarkSkillLocal(skillId: Int){
        skillDao.addBookmarkSkillLocal(skillId)
    }
    /**
     * 로컬에 있는 즐겨찾기 몬스터 조회
     */
    override suspend fun getAllBookmarkMonstersLocal(): List<MonsterDto>{
        return monsterDao.getAllBookmarkMonstersLocal().map { it.toDomain() }
    }
    /**
     * 즐겨찾기 몬스터 조회
     */
    override suspend fun addBookmarkMonsterLocal(monsterId: Int){
        monsterDao.addBookmarkMonsterLocal(monsterId)
    }


}