package com.gumigames.domain.model.basic

import com.google.gson.annotations.SerializedName

data class AuthDto(
    val accessToken: String,
    val refreshToken: String,
)
