package com.gumigames.data.datasource.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_monster")
data class MonsterEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val hp: Int,
    val description: String,
    val imgPath: String,
    val isBookmarked: Boolean
)
