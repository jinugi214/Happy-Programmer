package com.gumigames.data.entity.response

import com.google.gson.annotations.SerializedName

data class ErrorDto(
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String,
)
