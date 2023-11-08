package com.gumigames.data.datasource.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gumigames.data.datasource.entity.ItemEntity
import com.gumigames.data.datasource.entity.SkillEntity

@Dao
interface SkillDao {

    @Query("SELECT * FROM table_skill")
    suspend fun getAllSkillsLocal(): List<SkillEntity>

    @Query("SELECT * FROM table_skill WHERE name LIKE '%' || :keyword || '%'")
    suspend fun searchSkillsLocal(keyword: String): List<SkillEntity>

    @Query("SELECT * FROM table_skill WHERE isBookmarked = 1")
    suspend fun getAllBookmarkSkillsLocal(): List<SkillEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllSkillsLocal(skillList: List<SkillEntity>)

    // bookmark 저장 -> 해당 skill의 isBookmarked를 true로 변경
    @Query("UPDATE table_skill SET isBookmarked = 1 WHERE id = :skillId")
    suspend fun addBookmarkSkillLocal(skillId: Int)

    // bookmark 삭제 -> 해당 skill의 isBookmarked를 false로 변경
    @Query("UPDATE table_skill SET isBookmarked = 0 WHERE id = :skillId")
    suspend fun deleteBookmarkSkillLocal(skillId: Int)
}