package com.gumigames.data.datasource.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gumigames.data.datasource.entity.ItemEntity

@Dao
interface ItemDao {

    @Query("SELECT * FROM table_item")
    suspend fun getAllItemsLocal(): List<ItemEntity>

    @Query("SELECT * FROM table_item WHERE name LIKE '%' || :keyword || '%'")
    suspend fun searchItemsLocal(keyword: String): List<ItemEntity>

    @Query("SELECT * FROM table_item WHERE isBookmarked = 1")
    suspend fun getAllBookmarkItemsLocal(): List<ItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllItemsLocal(itemList: List<ItemEntity>)

    // bookmark 저장 -> 해당  item의 isBookmarked를 true로 변경
    @Query("UPDATE table_item SET isBookmarked = 1 WHERE id = :itemId")
    suspend fun addBookmarkItemLocal(itemId: Int)

    // bookmark 삭제 -> 해당 item의 isBookmarked를 false로 변경
    @Query("UPDATE table_item SET isBookmarked = 0 WHERE id = :itemId")
    suspend fun deleteBookmarkItemLocal(itemId: Int)
}