package com.gumigames.domain.repository

import com.gumigames.domain.model.item.ItemDto

interface ItemRepository {
    suspend fun getAllItems(): List<ItemDto>
    suspend fun getSearchItems(keyword: String): List<ItemDto>
}