package com.gumigames.data.datasource.entity

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_user")
data class UserEntity(
    @PrimaryKey
    val id: Int,
    val exp: Int,
    val gender: String,
    val imageBitmap: Bitmap?,
    val level: Int,
    val name: String,
    val point: Int,
    val savepoint: String,
    val storyProgress: Int
)