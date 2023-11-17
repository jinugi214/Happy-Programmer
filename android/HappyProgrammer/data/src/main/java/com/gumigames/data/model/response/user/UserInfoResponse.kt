package com.gumigames.data.model.response.user


import com.google.gson.annotations.SerializedName

data class UserInfoResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("exp")
    val exp: Int,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("imgPath")
    val imgPath: String,
    @SerializedName("level")
    val level: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("point")
    val point: Int,
    @SerializedName("savepoint")
    val savepoint: String,
    @SerializedName("storyProgress")
    val storyProgress: Int
)