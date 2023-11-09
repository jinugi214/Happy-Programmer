package com.gumigames.data.repository

import android.util.Log
import com.gumigames.data.datasource.dao.ItemDao
import com.gumigames.data.datasource.dao.MonsterDao
import com.gumigames.data.datasource.dao.SkillDao
import com.gumigames.data.mapper.toData
import com.gumigames.data.mapper.toDomain
import com.gumigames.data.service.ItemService
import com.gumigames.data.service.MonsterService
import com.gumigames.data.service.SkillService
import com.gumigames.data.util.handleApi
import com.gumigames.domain.model.common.ItemDto
import com.gumigames.domain.model.common.MonsterDto
import com.gumigames.domain.model.common.SkillDto
import com.gumigames.domain.repository.DogamRepository

private const val TAG = "차선호"
class DogamRepositoryImpl(
    private val itemService: ItemService,
    private val skillService: SkillService,
    private val monsterService: MonsterService,
    private val itemDao: ItemDao,
    private val skillDao: SkillDao,
    private val monsterDao: MonsterDao
): DogamRepository {

    /////////////////////////////////////// 아이템 /////////////////////////////////////////////
    /**
     * 아이템 전체 조회
     */
    override suspend fun getAllItems(): List<ItemDto> {
        return handleApi { itemService.getAllItems() }.map { it.toDomain() }
    }

    /**
     * 아이템 검색
     */
    override suspend fun searchItems(keyword: String): List<ItemDto> {
        return handleApi { itemService.searchItems(keyword) }.map { it.toDomain() }
    }
    /**
     * 아이템 전체 로컬에 저장
     */
    override suspend fun insertAllItemsLocal(itemList: List<ItemDto>) {
        Log.d(TAG, "insertAllItemsLocal 시작... $itemList")
        itemDao.insertAllItemsLocal(itemList.map { it.toData() })
        Log.d(TAG, "insertAllItemsLocal 끝...")
    }

    /**
     * 로컬에 있는 전체 아이템 조회
     */
    override suspend fun getAllItemsLocal(): List<ItemDto> {
        return itemDao.getAllItemsLocal().map { it.toDomain() }
    }

    /**
     * 로컬에 있는 아이템 검색
     */
    override suspend fun searchItemsLocal(keyword: String): List<ItemDto> {
        return itemDao.searchItemsLocal(keyword).map { it.toDomain() }
    }

    /**
     * 로컬에 있는 즐겨찾기 아이쳄 조회
     */
    override suspend fun getAllBookmarkItemsLocal(): List<ItemDto> {
        return itemDao.getAllBookmarkItemsLocal().map { it.toDomain() }
    }
    /**
     * 즐겨찾기 토글
     */
    override suspend fun toggleBookmarkItem(itemId: Int): Boolean {
        return handleApi { itemService.toggleBookmarkItem(itemId) }.toDomain()
    }

    /**
     * 즐겨찾기 아이템 로컬에 저장
     */
    override suspend fun addBookmarkItemLocal(itemId: Int) {
        itemDao.addBookmarkItemLocal(itemId)
    }

    /////////////////////////////////////// 스킬 /////////////////////////////////////////////

    /**
     * 스킬 전체 조회
     */
    override suspend fun getAllSkills(): List<SkillDto> {
        return handleApi { skillService.getAllSkills() }.map { it.toDomain() }
    }

    /**
     * 스킬 검색
     */
    override suspend fun searchSkills(keyword: String): List<SkillDto> {
        return handleApi { skillService.searchSkills(keyword) }.map { it.toDomain() }
    }
    /**
     * 스킬 전체 로컬에 저장
     */
    override suspend fun insertAllSkillsLocal(skillList: List<SkillDto>) {
        skillDao.insertAllSkillsLocal(skillList.map { it.toData() })
    }

    /**
     * 로컬에 있는 전체 스킬 조회
     */
    override suspend fun getAllSkillsLocal(): List<SkillDto> {
        return skillDao.getAllSkillsLocal().map { it.toDomain() }
    }

    /**
     * 로컬에 있는 스킬 검색
     */
    override suspend fun searchSkillsLocal(keyword: String): List<SkillDto> {
        return skillDao.searchSkillsLocal(keyword).map { it.toDomain() }
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



    /////////////////////////////////////// 몬스터 /////////////////////////////////////////////



    /**
     * 몬스터 전체 조회
     */
    override suspend fun getAllMonsters(): List<MonsterDto> {
        return handleApi { monsterService.getAllMonsters() }.map { it.toDomain() }
    }

    /**
     * 몬스터 검색
     */
    override suspend fun searchMonsters(keyword: String): List<MonsterDto> {
        return handleApi { monsterService.searchMonsters(keyword) }.map { it.toDomain() }
    }
    /**
     * 몬스터 전체 로컬에 저장
     */
    override suspend fun insertAllMonstersLocal(monsterList: List<MonsterDto>) {
        monsterDao.insertAllMonstersLocal(monsterList.map { it.toData() })
    }

    /**
     * 로컬에 있는 전체 몬스터 조회
     */
    override suspend fun getAllMonstersLocal(): List<MonsterDto> {
        return monsterDao.getAllMonstersLocal().map { it.toDomain() }
    }

    /**
     * 로컬에 있는 몬스터 검색
     */
    override suspend fun searchMonstersLocal(keyword: String): List<MonsterDto> {
        return monsterDao.searchMonstersLocal(keyword).map { it.toDomain() }
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