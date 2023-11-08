package com.gumigames.domain.repository

import com.gumigames.domain.model.common.ItemDto
import com.gumigames.domain.model.common.MonsterDto
import com.gumigames.domain.model.common.SkillDto

interface DogamRepository {
    /**
     * 아이템 전체 조회
     */
    suspend fun getAllItems(): List<ItemDto>
    /**
     * 아이템 검색
     */
    suspend fun getSearchItems(keyword: String): List<ItemDto>
    /**
     * 스킬 전체 조회
     */
    suspend fun getAllSkills(): List<SkillDto>
    /**
     * 스킬 검색
     */
    suspend fun getSearchSkills(keyword: String): List<SkillDto>
    /**
     * 몬스터 전체 조회
     */
    suspend fun getAllMonsters(): List<MonsterDto>
    /**
     * 몬스터 검색
     */
    suspend fun getSearchMonsters(keyword: String): List<MonsterDto>

    /////////////// 로컬 /////////////////////////////////

    /**
     * 로컬에 있는 즐겨찾기 아이템 조회
     */
    suspend fun getAllBookmarkItemsLocal(): List<ItemDto>
    /**
     * 즐겨찾기 아이템 로컬에 저장
     */
    suspend fun addBookmarkItemLocal(itemId: Int)
    /**
     * 로컬에 있는 즐겨찾기 스킬 조회
     */
    suspend fun getAllBookmarkSkillsLocal(): List<SkillDto>
    /**
     * 즐겨찾기 스킬 로컬에 저장
     */
    suspend fun addBookmarkSkillLocal(skillId: Int)
    /**
     * 로컬에 있는 즐겨찾기 몬스터 조회
     */
    suspend fun getAllBookmarkMonstersLocal(): List<MonsterDto>
    /**
     * 즐겨찾기 몬스터 조회
     */
    suspend fun addBookmarkMonsterLocal(monsterId: Int)
}