package com.gumigames.domain.repository

import com.gumigames.domain.model.item.ItemDto

interface BookmarkRepository {
    /**
     * 로컬에 있는 즐겨찾기 아이템 조회
     */
    suspend fun getAllBookmarkItemLocal(): List<ItemDto>
    /**
     * 즐겨찾기 아이템 로컬에 저장
     */
    suspend fun addBookmarkItem(itemDto: ItemDto)
}