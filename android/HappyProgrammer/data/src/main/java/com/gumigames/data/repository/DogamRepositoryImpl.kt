package com.gumigames.data.repository

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
    private val monsterService: MonsterService
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



}