package com.gumigames.data.datasource.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gumigames.data.datasource.entity.MonsterBookmarkEntity
import com.gumigames.data.datasource.entity.SkillBookmarkEntity

@Dao
interface MonsterBookmarkDao {
    @Query("SELECT * FROM table_monster_bookmark")
    suspend fun getAllBookmarkMonstersLocal(): List<MonsterBookmarkEntity>

    // bookmark 저장 - 중복 값 충돌 발생 시 새로 들어온 데이터로 교체.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBookmarkMonsterLocal(bookmarkMonster: MonsterBookmarkEntity)

    // bookmark 삭제
    @Delete
    suspend fun deleteBookmarkMonsterLocal(bookmarkMonster: MonsterBookmarkEntity)
}