package com.gumigames.data.datasource.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gumigames.data.datasource.dao.ItemBookmarkDao
import com.gumigames.data.datasource.entity.ItemBookmarkEntity

@Database(entities = [ItemBookmarkEntity::class], version = 1)
abstract class BookmarkDatabase: RoomDatabase(){
    abstract fun itemBookmarkDao(): ItemBookmarkDao
}