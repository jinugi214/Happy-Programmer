package com.gumigames.data.repository

import com.gumigames.data.datasource.dao.ItemBookmarkDao
import com.gumigames.domain.repository.ItemRepository

class ItemRepositoryImpl(
    private val itemBookmarkDao: ItemBookmarkDao
): ItemRepository {
}