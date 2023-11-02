package com.gumigames.data.datasource.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gumigames.data.datasource.entity.ItemBookmarkEntity

@Dao
interface ItemBookmarkDao {

    @Query("SELECT * FROM table_item_bookmark")
    suspend fun getAllBookmarkItemLocal(): List<ItemBookmarkEntity>

    // bookmark 저장 - 중복 값 충돌 발생 시 새로 들어온 데이터로 교체.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBookmarkItem(bookmarkItem: ItemBookmarkEntity)

    // bookmark 삭제
    @Delete
    suspend fun deleteBookmarkItem(bookmarkItem: ItemBookmarkEntity)
}