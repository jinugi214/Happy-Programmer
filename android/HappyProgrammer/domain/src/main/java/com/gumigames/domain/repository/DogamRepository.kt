package com.gumigames.domain.repository

import com.gumigames.domain.model.item.ItemDto
import com.gumigames.domain.model.item.MonsterDto

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
     * 즐겨찾기 아이템 로컬에 저장
     */
    suspend fun addBookmarkItem(itemDto: ItemDto)
    /**
     * 몬스터 전체 조회
     */
    suspend fun getAllMonsters(): List<MonsterDto>
    /**
     * 몬스터 검색
     */
    suspend fun getSearchMonsters(keyword: String): List<MonsterDto>
}