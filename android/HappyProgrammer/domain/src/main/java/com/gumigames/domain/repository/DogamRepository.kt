package com.gumigames.domain.repository

import android.content.ClipData.Item
import com.gumigames.domain.model.common.ItemDto
import com.gumigames.domain.model.common.MonsterDto
import com.gumigames.domain.model.common.SkillDto

interface DogamRepository {

    //////////////////////////////// 아이템 ///////////////////////////////////////

    /**
     * 아이템 전체 조회
     */
    suspend fun getAllItems(): List<ItemDto>
    /**
     * 아이템 검색
     */
    suspend fun searchItems(keyword: String): List<ItemDto>

    /**
     * 아이템 전체 로컬에 저장
     */
    suspend fun insertAllItemsLocal(itemList: List<ItemDto>)
    /**
     * 로컬에 있는 전체 아이템 조회
     */
    suspend fun getAllItemsLocal(): List<ItemDto>
    /**
     * 로컬에 있는 아이템 검색
     */
    suspend fun searchItemsLocal(keyword: String): List<ItemDto>
    /**
     * 로컬에 있는 즐겨찾기 아이템 조회
     */
    suspend fun getAllBookmarkItemsLocal(): List<ItemDto>
    /**
     * 즐겨찾기 아이템 로컬에 저장
     */
    suspend fun addBookmarkItemLocal(itemId: Int)


    //////////////////////////////// 스킬 ///////////////////////////////////////

    /**
     * 스킬 전체 조회
     */
    suspend fun getAllSkills(): List<SkillDto>
    /**
     * 스킬 검색
     */
    suspend fun searchSkills(keyword: String): List<SkillDto>

    /**
     * 스킬 전체 로컬에 저장
     */
    suspend fun insertAllSkillsLocal(skillList: List<SkillDto>)
    /**
     * 로컬에 있는 전체 스킬 조회
     */
    suspend fun getAllSkillsLocal(): List<SkillDto>
    /**
     * 로컬에 있는 스킬 검색
     */
    suspend fun searchSkillsLocal(keyword: String): List<SkillDto>
    /**
     * 로컬에 있는 즐겨찾기 스킬 조회
     */
    suspend fun getAllBookmarkSkillsLocal(): List<SkillDto>
    /**
     * 즐겨찾기 스킬 로컬에 저장
     */
    suspend fun addBookmarkSkillLocal(skillId: Int)


    //////////////////////////////// 몬스터 ///////////////////////////////////////


    /**
     * 몬스터 전체 조회
     */
    suspend fun getAllMonsters(): List<MonsterDto>
    /**
     * 몬스터 검색
     */
    suspend fun searchMonsters(keyword: String): List<MonsterDto>

    /**
     * 몬스터 전체 로컬에 저장
     */
    suspend fun insertAllMonstersLocal(monsterList: List<MonsterDto>)
    /**
     * 로컬에 있는 전체 몬스터 조회
     */
    suspend fun getAllMonstersLocal(): List<MonsterDto>
    /**
     * 로컬에 있는 몬스터 검색
     */
    suspend fun searchMonstersLocal(keyword: String): List<MonsterDto>
    /**
     * 로컬에 있는 즐겨찾기 몬스터 조회
     */
    suspend fun getAllBookmarkMonstersLocal(): List<MonsterDto>
    /**
     * 즐겨찾기 몬스터 조회
     */
    suspend fun addBookmarkMonsterLocal(monsterId: Int)
}