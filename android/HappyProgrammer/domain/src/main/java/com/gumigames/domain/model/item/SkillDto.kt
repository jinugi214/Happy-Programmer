package com.gumigames.domain.model.item


import com.google.gson.annotations.SerializedName

data class SkillDto(
    val id: Int,
    val name: String,
    val description: String,
    val imgPath: String,
)