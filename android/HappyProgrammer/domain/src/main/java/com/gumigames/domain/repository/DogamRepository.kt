package com.gumigames.domain.repository

import com.gumigames.domain.model.item.ItemDto
import com.gumigames.domain.model.item.MonsterDto
import com.gumigames.domain.model.item.SkillDto

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
}