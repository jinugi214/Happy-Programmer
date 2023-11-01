package com.gumigames.data.datasource.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_item_bookmark")
data class ItemBookmarkEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val imageRes: String
)
