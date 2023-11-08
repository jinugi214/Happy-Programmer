package com.gumigames.data.datasource.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_skill")
data class SkillEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val imgPath: String,
    val isBookmarked: Boolean
)
