package com.gumigames.data.datasource.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.gumigames.data.datasource.dao.ItemBookmarkDao
import com.gumigames.data.datasource.entity.ItemBookmarkEntity
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

@Database(entities = [ItemBookmarkEntity::class], version = 1)
abstract class BookmarkDatabase: RoomDatabase(){
    abstract fun itemBookmarkDao(): ItemBookmarkDao

}