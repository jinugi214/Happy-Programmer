package com.gumigames.data.entity.response

import com.google.gson.annotations.SerializedName

data class AuthDto(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("refreshToken")
    val refreshToken: String = "",
)
