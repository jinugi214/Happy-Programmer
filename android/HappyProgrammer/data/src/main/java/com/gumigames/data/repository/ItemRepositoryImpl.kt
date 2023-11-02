package com.gumigames.data.repository

import com.gumigames.data.datasource.dao.ItemBookmarkDao
import com.gumigames.data.mapper.toData
import com.gumigames.data.mapper.toDomain
import com.gumigames.data.service.ItemService
import com.gumigames.data.util.handleApi
import com.gumigames.domain.model.item.ItemDto
import com.gumigames.domain.repository.ItemRepository

class ItemRepositoryImpl(
    private val itemBookmarkDao: ItemBookmarkDao,
    private val itemService: ItemService
): ItemRepository {
    override suspend fun getAllItems(): List<ItemDto> {
        return handleApi { itemService.getAllItems() }.map { it.toDomain() }
    }

    override suspend fun getSearchItems(keyword: String): List<ItemDto> {
        return handleApi { itemService.getSearchItems(keyword) }.map { it.toDomain() }
    }

    override suspend fun addBookmarkItem(itemDto: ItemDto) {
        itemBookmarkDao.addBookmarkItem(itemDto.toData())
    }

}