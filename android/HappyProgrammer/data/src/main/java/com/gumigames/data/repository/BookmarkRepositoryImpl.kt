package com.gumigames.data.repository

import com.gumigames.data.datasource.dao.ItemBookmarkDao
import com.gumigames.data.mapper.toData
import com.gumigames.data.mapper.toDomain
import com.gumigames.domain.model.item.ItemDto
import com.gumigames.domain.repository.BookmarkRepository

class BookmarkRepositoryImpl(
    private val itemBookmarkDao: ItemBookmarkDao
): BookmarkRepository {
    override suspend fun getAllBookmarkItemLocal(): List<ItemDto> {
        return itemBookmarkDao.getAllBookmarkItemLocal().map { it.toDomain() }
    }

    override suspend fun addBookmarkItem(itemDto: ItemDto) {
        itemBookmarkDao.addBookmarkItem(itemDto.toData())
    }


}