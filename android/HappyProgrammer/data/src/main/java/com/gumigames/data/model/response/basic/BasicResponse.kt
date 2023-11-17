package com.gumigames.data.model.response.basic

import com.google.gson.annotations.SerializedName

data class BasicResponse(
    @SerializedName("result")
    val result: String
)
