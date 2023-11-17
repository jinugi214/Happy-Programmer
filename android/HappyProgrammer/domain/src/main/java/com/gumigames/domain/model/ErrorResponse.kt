package com.gumigames.domain.model


import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("description")
    val description: String,
    @SerializedName("errorCode")
    val errorCode: String,
    @SerializedName("status")
    val status: Int
)