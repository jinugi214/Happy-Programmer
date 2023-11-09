package com.gumigames.data.model.response.bookmark


import com.google.gson.annotations.SerializedName

data class BookmarkResponse(
    @SerializedName("favorite")
    val favorite: Boolean
)