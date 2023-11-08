package com.gumigames.data.model.response.common


import com.google.gson.annotations.SerializedName

data class SkillResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("imgPath")
    val imgPath: String,
    @SerializedName("favorite")
    val isBookmarked: Boolean
)