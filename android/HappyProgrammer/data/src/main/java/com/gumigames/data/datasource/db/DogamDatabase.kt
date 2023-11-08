package com.gumigames.data.datasource.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gumigames.data.datasource.dao.ItemDao
import com.gumigames.data.datasource.dao.MonsterDao
import com.gumigames.data.datasource.dao.SkillDao
import com.gumigames.data.datasource.entity.ItemEntity
import com.gumigames.data.datasource.entity.MonsterEntity
import com.gumigames.data.datasource.entity.SkillEntity

/**
 * version 관리
 * 1 -> 최초
 * 2 -> SkillBookmarkEntity 추가
 * 3 -> MonsterBookmarkEntity 추가
 * 4 -> item, skill, monster 테이블 명 변경(즐겨찾기가 아니라 전체 조회 하려고)
 */
@Database(entities = [ItemEntity::class, SkillEntity::class, MonsterEntity::class], version = 4)
abstract class DogamDatabase: RoomDatabase(){
    abstract fun itemBookmarkDao(): ItemDao

    abstract fun skillBookmarkDao(): SkillDao

    abstract fun monsterBookmarkDao(): MonsterDao

}