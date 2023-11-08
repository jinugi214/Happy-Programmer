package com.gumigames.data.datasource.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gumigames.data.datasource.entity.ItemEntity
import com.gumigames.data.datasource.entity.MonsterEntity

@Dao
interface MonsterDao {

    @Query("SELECT * FROM table_monster")
    suspend fun getAllMonstersLocal(): List<MonsterEntity>

    @Query("SELECT * FROM table_monster WHERE isBookmarked = 1")
    suspend fun getAllBookmarkMonstersLocal(): List<MonsterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMonstersLocal(monsterList: List<MonsterEntity>)

    // bookmark 저장 -> 해당 monster의 isBookmarked를 true로 변경
    @Query("UPDATE table_monster SET isBookmarked = 1 WHERE id = :monsterId")
    suspend fun addBookmarkMonsterLocal(monsterId: Int)

    // bookmark 삭제 -> 해당 monster의 isBookmarked를 false로 변경
    @Query("UPDATE table_monster SET isBookmarked = 0 WHERE id = :monsterId")
    suspend fun deleteBookmarkMonsterLocal(monsterId: Int)
}