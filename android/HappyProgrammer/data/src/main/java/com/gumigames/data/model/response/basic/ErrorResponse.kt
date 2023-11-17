package com.gumigames.data.model.response.basic


import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("error")
    val error: String,
    @SerializedName("errorCode")
    val errorCode: String,
    @SerializedName("path")
    val path: String,
    @SerializedName("status")
    val status: Int
)