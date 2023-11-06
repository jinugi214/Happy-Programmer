package com.gumigames.data.model.response.common


import com.google.gson.annotations.SerializedName

data class MonsterResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("hp")
    val hp: Int,
    @SerializedName("imgPath")
    val imgPath: String,
    @SerializedName("name")
    val name: String
)