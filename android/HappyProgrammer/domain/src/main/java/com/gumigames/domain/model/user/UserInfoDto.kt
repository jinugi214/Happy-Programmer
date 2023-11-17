package com.gumigames.domain.model.user

import android.graphics.Bitmap
import com.google.gson.annotations.SerializedName

data class UserInfoDto(
    val id: Int,
    val exp: Int,
    val gender: String,
    val imageBitmap: Bitmap?,
    val level: Int,
    val name: String,
    val point: Int,
    val savepoint: String,
    val storyProgress: Int
)
