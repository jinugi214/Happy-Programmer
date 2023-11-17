package com.gumigames.data.datasource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gumigames.data.datasource.entity.SkillEntity
import com.gumigames.data.datasource.entity.UserEntity
import com.gumigames.data.model.response.user.UserInfoResponse
import com.gumigames.domain.model.user.UserInfoDto

@Dao
interface UserDao {
    @Query("SELECT * FROM table_user")
    suspend fun getUserInfo(): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserInfoLocal(userEntity: UserEntity)
}