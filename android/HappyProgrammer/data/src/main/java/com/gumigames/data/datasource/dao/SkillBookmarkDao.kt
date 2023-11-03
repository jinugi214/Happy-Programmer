package com.gumigames.data.datasource.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gumigames.data.datasource.entity.SkillBookmarkEntity

@Dao
interface SkillBookmarkDao {

    @Query("SELECT * FROM table_skill_bookmark")
    suspend fun getAllBookmarkSkillLocal(): List<SkillBookmarkEntity>

    // bookmark 저장 - 중복 값 충돌 발생 시 새로 들어온 데이터로 교체.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBookmarkSkillLocal(bookmarkSkill: SkillBookmarkEntity)

    // bookmark 삭제
    @Delete
    suspend fun deleteBookmarkSkillLocal(bookmarkSkill: SkillBookmarkEntity)
}