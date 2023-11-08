package com.gumigames.domain.model.common

import com.google.gson.annotations.SerializedName

data class MonsterDto(
    val id: Int,
    val name: String,
    val hp: Int,
    val description: String,
    val imgPath: String,
    val isBookmarked: Boolean
)
