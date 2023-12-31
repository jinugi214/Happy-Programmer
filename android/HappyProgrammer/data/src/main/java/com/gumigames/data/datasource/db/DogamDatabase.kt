package com.gumigames.data.datasource.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.gumigames.data.datasource.dao.ItemDao
import com.gumigames.data.datasource.dao.MonsterDao
import com.gumigames.data.datasource.dao.SkillDao
import com.gumigames.data.datasource.dao.UserDao
import com.gumigames.data.datasource.entity.ItemEntity
import com.gumigames.data.datasource.entity.MonsterEntity
import com.gumigames.data.datasource.entity.SkillEntity
import com.gumigames.data.datasource.entity.UserEntity

/**
 * version 관리
 * 1 -> 최초
 * 2 -> SkillBookmarkEntity 추가
 * 3 -> MonsterBookmarkEntity 추가
 * 4 -> item, skill, monster 테이블 명 변경(즐겨찾기가 아니라 전체 조회 하려고)
 */
@Database(entities = [ItemEntity::class, SkillEntity::class, MonsterEntity::class, UserEntity::class], version = 4)
@TypeConverters(BitmapTypeConverter::class)
abstract class DogamDatabase: RoomDatabase(){
    abstract fun itemDao(): ItemDao

    abstract fun skillDao(): SkillDao

    abstract fun monsterDao(): MonsterDao

    abstract fun userDao(): UserDao

}