package com.gumigames.domain.model.common

import com.google.gson.annotations.SerializedName

data class ItemDto(
    val id: Int,
    val name: String,
    val description: String,
    val imgPath: String,
    val isBookmarked: Boolean
)

