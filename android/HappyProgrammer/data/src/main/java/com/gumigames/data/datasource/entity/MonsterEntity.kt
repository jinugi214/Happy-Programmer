package com.gumigames.data.datasource.entity

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_monster")
data class MonsterEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val hp: Int,
    val description: String,
    val imageBitmap: Bitmap?,
    val isBookmarked: Boolean
)
