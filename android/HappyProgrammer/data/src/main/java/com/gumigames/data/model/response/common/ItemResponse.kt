package com.gumigames.data.model.response.common


import com.google.gson.annotations.SerializedName

data class ItemResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("imgPath")
    val imgPath: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("owned")
    val owned: Boolean,
    @SerializedName("favorite")
    val isBookmarked: Boolean
)