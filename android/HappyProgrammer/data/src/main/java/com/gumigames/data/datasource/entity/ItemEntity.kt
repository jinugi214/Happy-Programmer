package com.gumigames.data.datasource.entity

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_item")
data class ItemEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val imageBitmap: Bitmap,
//    val imgPath: String,
    val isBookmarked: Boolean
)
