package com.gumigames.data.datasource.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.gumigames.data.datasource.dao.ItemBookmarkDao
import com.gumigames.data.datasource.dao.SkillBookmarkDao
import com.gumigames.data.datasource.entity.ItemBookmarkEntity
import com.gumigames.data.datasource.entity.SkillBookmarkEntity
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

/**
 * version 관리
 * 1 -> 최초
 * 2 -> SkillBookmarkEntity 추가
 */
@Database(entities = [ItemBookmarkEntity::class, SkillBookmarkEntity::class], version = 2)
abstract class BookmarkDatabase: RoomDatabase(){
    abstract fun itemBookmarkDao(): ItemBookmarkDao

    abstract fun skillBookmarkDao(): SkillBookmarkDao

}