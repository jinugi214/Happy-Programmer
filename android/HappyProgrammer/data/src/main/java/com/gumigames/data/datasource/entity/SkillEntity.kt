package com.gumigames.data.datasource.entity

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_skill")
data class SkillEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val imageBitmap: Bitmap?,
    val isBookmarked: Boolean
)
