package com.gumigames.data.model.response


import com.google.gson.annotations.SerializedName

data class ItemResponse(
    @SerializedName("description")
    val description: String,
    @SerializedName("imgPath")
    val imgPath: String,
    @SerializedName("name")
    val name: String
)